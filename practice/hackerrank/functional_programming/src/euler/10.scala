package euler

import scala.collection.Searching._

object SumOfPrimes {

  val primes: Stream[Int] = 
    2 #:: Stream.from(3, 2).filter(x => primes.takeWhile(_ <= math.sqrt(x)).forall(x % _ != 0))

  val primeSums: Stream[(Int, Long)] = 
    (2, 2L) #:: (primes.tail zip primeSums).map { case (p, (_, s)) => (p, s + p.toLong) }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val T = in.next.toInt
    val ns = in.take(T).map(_.toInt)

    ns foreach { n =>
      val ans = primes.search(n) match {
        case Found(i) => primeSums(i)._2
        case InsertionPoint(i) => primeSums(i-1)._2
      }
      println(ans)
    } 

  }
}