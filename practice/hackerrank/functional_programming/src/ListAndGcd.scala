import scala.collection.SortedMap

object ListAndGcd {
  
  def gcd(a: Map[Int,Int], b: Map[Int,Int]): Map[Int,Int] = 
    a.filter(x => b.contains(x._1)).map{case (k, v) => (k, math.min(v, b(k)))}
  
  def gcdList(ns: List[Map[Int,Int]]): Map[Int,Int] = ns match {
    case List() => throw new IllegalArgumentException("zero-length list")
    case List(x) => x
    case x :: xs => gcd(x, gcdList(xs))
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    val q = in.next.toInt
    val ns = in.take(q).toList.map{ x => ( x.trim.split("\\s+") map {x => x.toInt} ).grouped(2).map(x => (x(0), x(1))).toMap }
    val solution = SortedMap(gcdList(ns).toSeq:_*)
    
    println(solution.map{case (k, v) => k + " " + v }.mkString(" "))
  }
}