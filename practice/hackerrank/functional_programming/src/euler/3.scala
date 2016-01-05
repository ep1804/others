package euler

object LargestPrimeFactor {
  
  def largestPrimeFactor(b : BigInt) = {
  def loop(f:BigInt, n: BigInt): BigInt =
     if (f == n) n else 
     if (n % f == 0) loop(f, n / f) 
     else loop(f + 1, n)
  loop (BigInt(2), b)
}
    
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    val T = in.next.toInt
    val ns = in.take(T).map(BigInt(_))
    
    ns map largestPrimeFactor foreach println
  }  
}