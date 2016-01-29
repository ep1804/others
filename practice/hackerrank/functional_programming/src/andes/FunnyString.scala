package andes

object FunnyString {
  
  def funny(s: String): Boolean = {
    def diff(s: String) = (s.tail zip s).map{case (a, b) => math.abs(a - b)}
    
    (diff(s) zip diff(s.reverse)).forall{ case (a, b) => a == b}
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ss = in.take(t)
    
    ss foreach {s => println(if(funny(s)) "Funny" else "Not Funny")}
  }
  
}