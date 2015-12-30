

import scala.collection.mutable.LinkedHashMap
import scala.collection.immutable.TreeMap

object MirkoConstructionSite {

  case class Line(id: Int, start: Int, slope: Int)

  def cross(l1: Line, l2: Line): Float = {
    (l1.start - l2.start).toFloat / (l2.slope - l1.slope).toFloat
  }

  def isInt(f: Float): Boolean = math.round(f).toFloat == f

  def buildMaps(ls: List[Line]) = {
    
    var points = Map[Int, Line]()
    var lines = Map[Line, Tuple2[Float, Float]]((ls(0), (Float.MinValue, Float.MaxValue)))

    for (i <- (1 until ls.length)) {
      val line = ls(i)
      val lineCross = lines.filter(x => { val cp = cross(x._1, line); cp >= x._2._1 && cp <= x._2._2 })
      val cp = cross(line, lineCross.head._1)

      // Update special points (points where lines cross)
      if (isInt(cp)) {
        val p = cp.toInt
        var lineAdd: Line = line
        if (points.contains(p) && points(p).id > line.id) lineAdd = points(p)
        points = points.filterKeys { x => x < p } + ((p, lineAdd))
      }

      // Update line segments
      val lineUpdated = lineCross.keySet.minBy(_.slope)
      val rangeUpdated = (lines(lineUpdated)._1, cp)
      val rangeAdd = (cp, Float.MaxValue)
      lines = lines.takeWhile(x => x._1.slope < lineUpdated.slope) + ((lineUpdated, rangeUpdated), (line, rangeAdd))
    }

    println(points)
    println(lines)
    
    val pointMap = TreeMap(points.toArray:_*)
    val lineMap = TreeMap(lines.map(x => (x._2._2, x._1)).toMap.toArray:_*)

    println(lineMap)
    
    (pointMap, lineMap)
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
        case (Line(i1, s1, l1), Line(i2, s2, l2)) => if (s1 > s2) true else if (s1 == s2 && i1 > i2) true else false
      }(0)
    }.toList.sortWith { case (Line(i1, s1, l1), Line(i2, s2, l2)) => l1 < l2 }

    println(sortedLines)

    buildMaps(sortedLines)
  }
}