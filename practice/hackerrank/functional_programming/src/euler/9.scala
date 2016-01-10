package euler

object SpecialPythagoreanTriplet {
  
  def maxProduct(n: Int): Int = {
    val ts = for{
      c <- (1 to (n - 2))
      b <- (1 until c)
      val a = n - c - b
      if (a > 0)
      if (a * a + b * b == c * c)      
    } yield a * b * c
    
    if(ts.isEmpty) -1
    else ts.max
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val T = in.next.toInt
    val ns = in.take(T).map(_.toInt)
    
    ns foreach {n => println(maxProduct(n))}
  }
  
}