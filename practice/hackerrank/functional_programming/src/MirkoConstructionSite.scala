

object MirkoConstructionSite {

  case class Bldg(id: Int, start: Int, slope: Int)

  def getMaxId(bs: List[Bldg], day: Int) = {
    bs.sortWith{
      case (Bldg(i1, s1, l1), Bldg(i2, s2, l2)) => { 
        val cmp = (s1 + l1 * day) - (s2 + l2 * day)
        if(cmp > 0) true
        else if(cmp == 0 && i1 > i2) true
        else false
      }
    }(0).id
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines

    val nq = in.next.split(" ").map(_.toInt)
    val starts = in.next.split(" ").map(_.toInt).toList
    val slopes = in.next.split(" ").map(_.toInt).toList
    val bds = ((starts zip slopes) zip (1 to nq(0))) map{case (a, b) => Bldg(b, a._1, a._2) }
    val qs = in.take(nq(1)).map(_.toInt).toList

    val efBds = bds.groupBy { x => x.slope }.map {
      case (slp, lines) => lines.sortWith{
        case (Bldg(i1, s1, l1), Bldg(i2, s2, l2)) => if(s1 > s2) true else if(s1 == s2 && i1 > i2) true else false   
      }(0)
    }.toList
    
    println(qs.map(getMaxId(efBds, _)).mkString("\n"))
  }
}