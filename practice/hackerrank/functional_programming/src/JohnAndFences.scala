import scala.annotation.tailrec
import scala.IndexedSeq

object JohnAndFences {
  
  def maxArea(ns: List[Int]): Int = {
    
    // ns: remaining fence height sequence
    // max: max area until now
    // hprev: previous height
    // boxes: currently growing boxes (area at each height)
    //
    // return: max
    @tailrec
    def scan(ns: List[Int], max: Int, hprev: Int, boxes: IndexedSeq[Int]): Int = ns match {
      case List() => max
      case x :: xs => {
        val arr = (0 to x).map{x => if(x <= math.min(x, hprev)) x + boxes(x) else x}
        scan(xs, math.max(max, arr.reduceLeft(_ max _)), x, arr)
      }
    }
    
    scan(ns, 0, 0, IndexedSeq(0))
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines    
    val ns = in.drop(1).next.trim.split(" ").map(_.toInt).toList
    println(maxArea(ns))
  }
}