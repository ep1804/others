import scala.collection.immutable.TreeMap
import scala.collection.mutable.LinkedHashMap

// Height of building given number of elapsed days is considered as a line on day-height plane

object MirkoConstructionSite {

  case class Line(id: Int, start: Int, slope: Int)

  def cross(l1: Line, l2: Line): Float =
    (l1.start - l2.start).toFloat / (l2.slope - l1.slope).toFloat

  def isInt(f: Float): Boolean = math.round(f).toFloat == f

  def buildMaps(ls: List[Line]) = {

    // x coordinate of point's IDs where lines cross, x >= 0
    val points = LinkedHashMap[Int, Int]()

    // lines at the top with its dominant range, initialized with the first line
    var lines = Map[Line, Tuple2[Float, Float]]((ls(0), (Float.MinValue, Float.MaxValue)))

    // update points and lines with remaining lines
    for (i <- (1 until ls.length)) {
      val line = ls(i)
      val linesCross = lines.filter { case (l, rg) => { val cp = cross(l, line); cp >= rg._1 && cp <= rg._2 } }
      val cp = cross(line, linesCross.head._1)

      if (cp < 0) {

        // This case, current line is above all other lines. so re-initialize all
        points.clear()
        lines = Map[Line, Tuple2[Float, Float]]((line, (Float.MinValue, Float.MaxValue)))

      } else {
        
        // Update points
        points.retain((k, _) => k < cp)
        if (isInt(cp)) {
          val p = cp.toInt
          if (points.contains(p)){
            if(points(p) < line.id)
              points += ((p, line.id))
          }else{
            val id0 = linesCross.map(_._1).maxBy(_.id).id
            if(id0 > line.id)
              points += ((p, id0))
            else
              points += ((p, line.id))
          }
        }

        // Update line segments
        val lineUpdated = linesCross.keySet.minBy(_.slope)
        val rangeUpdated = (lines(lineUpdated)._1, cp)
        val rangeAdd = (cp, Float.MaxValue)
        lines = lines.filter(_._1.slope < lineUpdated.slope) + ((lineUpdated, rangeUpdated), (line, rangeAdd))
      }
    }

    //println(points)
    //println(lines)

    val lineMap = TreeMap(lines.map{case (l, rg) => (rg._2, l)}.toMap.toArray: _*)
    //println(lineMap)

    (points, lineMap)
  }

  def search(pm: LinkedHashMap[Int, Int], lm: TreeMap[Float, Line])(x: Int): Int = {
    if (pm.contains(x)) return pm(x)
    else lm.from(x.toFloat).head._2.id
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines

    val nq = in.next.split(" ").map(_.toInt)
    val starts = in.next.split(" ").map(_.toInt).toList
    val slopes = in.next.split(" ").map(_.toInt).toList
    val lines = ((starts zip slopes) zip (1 to nq(0))) map { case (a, b) => Line(b, a._1, a._2) }
    val qs = in.take(nq(1)).map(_.toInt).toList

    val sortedLines = lines.groupBy { x => x.slope }.map {
      case (slp, ls) => ls.sortWith {
        case (Line(i1, s1, _), Line(i2, s2, _)) => if (s1 > s2) true else if (s1 == s2 && i1 > i2) true else false
      }.head
    }.toList.sortWith { case (Line(i1, s1, l1), Line(i2, s2, l2)) => l1 < l2 }

    //println(sortedLines)

    val (pointMap, lineMap) = buildMaps(sortedLines)

    val sch = search(pointMap, lineMap)_

    println(qs.map(sch(_)).mkString("\n"))
  }
}