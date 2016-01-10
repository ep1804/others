package euler

object SpecialPythagoreanTriplet {
  
  def maxProduct(n: Int): Int = {
    val ts = for{
      a <- (1 to (n - 2))
      if (n > 2 * a)
      val b = n.toFloat * (n - 2 * a).toFloat / 2 / (n - a)
      if (b % 1 == 0)
      val c = n - a - b.toInt
      if (c > 0)
    } yield a * b.toInt * c
    
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