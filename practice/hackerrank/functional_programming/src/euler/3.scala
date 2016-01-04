package euler

object LargestPrimeFactor {
  
  lazy val from3: Stream[Long] = 3 #:: from3.map(_ + 1)
  
  lazy val primes: Stream[Int] = 2 #:: from3.filter(i => primes.takeWhile(j => j * j <= i).forall(k => i % k > 0)).map(_.toInt)
  
//  def maxFactor(n: Long): Long = {
//    lazy val ns: Stream[Long] = (n) #:: ns.map(x => x - 1)
//    ns.takeWhile(n % _ == 0).takeWhile(isPrime)(0)
//  }
  
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