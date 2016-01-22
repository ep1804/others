package euler

object PowerDigitSum {
  
  lazy val pow2: Stream[BigInt] = BigInt(2) #:: pow2.map(_ * 2)
  
  def powerDigitSum(n: Int): Int = 
    pow2(n - 1).toString().map(_ - '0').sum 
    // pow2.apply takes linear time. 
    // further optimization is possible by caching this to array.
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ns = in.take(t).map(_.toInt)

    println(pow2.take(5).toList)
    
    ns foreach {n => println(powerDigitSum(n))}
  }
}