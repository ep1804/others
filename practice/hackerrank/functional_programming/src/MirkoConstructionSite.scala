

import scala.collection.mutable.LinkedHashMap
import scala.collection.immutable.TreeMap
import scala.collection.mutable.ListBuffer

object MirkoConstructionSite {

  case class Line(id: Int, start: Int, slope: Int)
  
  def cross(l1: Line, l2: Line): Float = {
    (l1.start - l2.start).toFloat / (l2.slope - l1.slope).toFloat 
  }
  
  val specialPointMap = LinkedHashMap[Int, Line]()
  val pointMap = TreeMap[Float, (Line, Line)]()

  val lines = ListBuffer[Line]()
  val ranges = ListBuffer[Tuple2[Float, Float]]()
  
  def buildPoints(ls: List[Line]) = {
    
    lines += ls(0)
    ranges += ((Float.MinValue, Float.MaxValue))

    for(i <- (2 until ls.length)){
      val line = ls(i)
      val cps = lines.filter(x => {
        val cp = cross(x, line)
        if(cp >= crossPoints(i - 1)
        } )
      }
      
    }
    
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines

    val nq = in.next.split(" ").map(_.toInt)
    val starts = in.next.split(" ").map(_.toInt).toList
    val slopes = in.next.split(" ").map(_.toInt).toList
    val lines = ((starts zip slopes) zip (1 to nq(0))) map{case (a, b) => Line(b, a._1, a._2) }
    val qs = in.take(nq(1)).map(_.toInt).toList

    val sortedLines = lines.groupBy { x => x.slope }.map {
      case (slp, ls) => ls.sortWith{
        case (Line(i1, s1, l1), Line(i2, s2, l2)) => if(s1 > s2) true else if(s1 == s2 && i1 > i2) true else false   
      }(0)
    }.toList.sortWith{ case (Line(i1, s1, l1), Line(i2, s2, l2)) => l1 < l2 }
    
    println(sortedLines)
    
    buildPoints(sortedLines)
  }
}