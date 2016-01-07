package euler

object NthPrime {
  
  lazy val primes: Stream[Int] = 
    2 #:: Stream.from(3, 2).filter(x => primes.takeWhile(_ <= math.sqrt(x)).forall(x % _ != 0))
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    val T = in.next.toInt
    val ns = in.take(T).map(_.toInt)
    
    ns foreach(n => println(primes(n-1)))
  }  
}