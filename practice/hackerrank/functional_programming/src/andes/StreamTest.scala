package andes

//https://www.hackerrank.com/contests/30-days-of-code/challenges/day-5-loops

object StreamTest {
  
  def sequence(a: Int, b: Int): Stream[Int] = Stream.from(1).map(x => (a + (math.pow(2, x) - 1) * b).toInt)
    
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    val T = in.next.trim.toInt
    val abn = in.take(T).map(x => x.trim.split("\\s+").map(_.toInt).toList)
    
    abn map {case List(a, b, n) => sequence(a, b).take(n)} foreach(x => println(x.mkString(" ")))
  }  
}