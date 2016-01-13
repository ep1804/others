package euler

object LargestPrimeFactor {

  lazy val from3by2: Stream[Long] = 3L #:: from3by2.map(_ + 2)
  
  lazy val primes: Stream[Long] = 
    2L #:: from3by2.filter(i => primes.takeWhile(j => j * j <= i).forall(i % _ > 0))

  // c.f.
  //def isPrime(n: Long): Boolean = primes.takeWhile(_ <= math.sqrt(n)).filter(n % _ == 0).size == 0

  // repeat division by p until there's no p factor in n
  def divRep(n: Long, p: Long): Long = 
    if(n % p != 0) n
    else divRep(n / p, p)
  
  def solve(n: Long): Long = {
      
    // if n is prime, return n
    val ps = primes.takeWhile(_ <= math.sqrt(n)).filter(n % _ == 0) // small prime factors
    if(ps.size == 0) return n  // n is prime
    
    // remove small prime factors from n -> n2
    val n2 = ps.foldLeft(n)(divRep(_ , _))
    
    if(n2 == 1) ps.last
    else solve(n2)
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines

    val T = in.next.toInt
    val ns = in.take(T).map(_.toLong) 
    
    ns map solve foreach println
  }
}