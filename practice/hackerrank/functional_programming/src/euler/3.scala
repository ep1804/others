package euler

object LargestPrimeFactor {

  lazy val from3by2: Stream[Long] = 3L #:: from3by2.map(_ + 2)
  
  lazy val primes: Stream[Long] = 
    2L #:: from3by2.filter(i => primes.takeWhile(j => j * j <= i).forall(i % _ > 0))

  // c.f.
  //def isPrime(n: Long): Boolean = primes.dropWhile(n % _ != 0)(0) == n

  // repeat division by p until there's no p factor in n
  def divRep(n: Long, p: Long): Long = 
    if(n % p != 0) n
    else divRep(n / p, p)
    
  def solve(n: Long): Long = {
    val p = primes.dropWhile(n % _ != 0)(0) // smallest prime factor
    if( p == n ) n
    else {
      val n2 = divRep(n, p)
      if(n2 == 1) p
      else solve(n2)
    }    
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines

    val T = in.next.toInt
    val ns = in.take(T).map(_.toLong) 
    
    ns map solve foreach println
  }
}