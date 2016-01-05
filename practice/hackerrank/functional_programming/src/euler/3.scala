package euler

object LargestPrimeFactor {
  
  lazy val from2: Stream[Long] = 2L #:: from2.map(_ + 1)
  
  def sieve(s: Stream[Long]): Stream[Long] = s.head #:: sieve(s.tail.filter( _ % s.head != 0))
  
  val primes = sieve(from2)
    
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    val T = in.next.toInt
    val ns = in.take(T).map(_.toLong)
    
    ns foreach ( x => {      
      val primeFactors = primes.takeWhile(_ <= x).filter(p => x % p == 0)
      println(primeFactors.last)
    })
  }  
}