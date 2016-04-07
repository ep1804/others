package euler

import scala.collection.Searching._

object PandigitalPrime {

  lazy val primes: Stream[Int] = 
    2 #:: Stream.from(3, 2).filter(x => primes.takeWhile(_ <= math.sqrt(x)).forall(x % _ != 0))
    
  def isPrime(n: Long): Boolean = {
    if(n == 1) false
    else primes.takeWhile(_ <= math.sqrt(n)).forall(n % _ != 0)
  }
  
  // TODO you need to find a better way than using 'permutations' function
  val panPrimes = 
    (1 to 9).flatMap{n => (1 to n).mkString("").permutations.map(_.toLong).filter(isPrime)}

  def solve(n: Long): Long = {

    panPrimes.search(n) match {
      case Found(i) => panPrimes(i)
      case InsertionPoint(i) => {
        if(i == 0) -1
        else panPrimes(i - 1)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ns = in.take(t).map(_.toInt)

    ns foreach { n => println(solve(n)) }
  }
}