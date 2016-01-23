package euler

object E020 {
  
  lazy val factorial: Stream[BigInt] = 
    0 #:: 1 #:: (Stream.from(2) zip factorial.tail).map{case (a, b) => a * b }
  lazy val digitSum = factorial.map(x => x.toString.map(_ - '0').sum) 
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.trim.toInt
    val ns = in.take(t).map(_.trim.toInt)
    
    val sol = digitSum.take(1001).toArray
    
    ns foreach (n => println(sol(n)))
  }
}