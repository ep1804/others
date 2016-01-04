package euler

object LargestPrimeFactor {
  
  lazy val from3: Stream[Long] = 
    3L #:: from3.map(_ + 1)
  
  lazy val primes: Stream[Long] = 
    2L #:: from3.filter(i => primes.takeWhile(j => j * j <= i).forall(k => i % k > 0))
    
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