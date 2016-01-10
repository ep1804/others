package euler

object SpecialPythagoreanTriplet {
  
  def maxProduct(lo: Int, hi: Int): Int = {
    val ts = for{
      c <- (lo to hi)
      b <- (lo until c)
      a <- (lo until b)
      if (a + b + c == hi)
      if (a * a + b * b == c * c)      
    } yield a * b * c
    
    if(ts.isEmpty) -1
    else ts.max
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val T = in.next.toInt
    val ns = in.take(T).map(_.toInt)
    
    ns foreach {n => println(maxProduct(1, n))}
  }
  
}