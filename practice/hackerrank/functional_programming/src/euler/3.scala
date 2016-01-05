package euler

object LargestPrimeFactor {
  
  lazy val from3by2: Stream[Long] = 3L #:: from3by2.map(_ + 2)
  
  lazy val primes: Stream[Long] = 
    2L #:: from3by2.filter(i => primes.takeWhile(j => j * j <= i).forall(k => i % k > 0))
    
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    val T = in.next.toInt
    val ns = in.take(T).map(_.toLong)
    
    ns foreach ( x => {
      val primeFactors = primes.takeWhile(_ <= x).filter(x % _ == 0)
      println(primeFactors.last)
    })
  }  
}