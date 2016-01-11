package euler

import scala.collection.Searching._

object SumOfPrimes {

  lazy val primes: Stream[Int] = 
    2 #:: Stream.from(3, 2).filter(x => primes.takeWhile(_ <= math.sqrt(x)).forall(x % _ != 0))
    
  lazy val primeSums: Stream[Long] = 
    2L #:: (primes.tail zip primeSums).map { case (p, s) => p.toLong + s }

  val ps = primes.takeWhile(_ < 1000000).toIndexedSeq
  val pss = primeSums.take(ps.length).toIndexedSeq
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val T = in.next.toInt
    val ns = in.take(T).map(_.toInt)
    
    ns foreach { n =>
      val ans = ps.search(n) match {
        case Found(i) => pss(i)
        case InsertionPoint(i) => pss(i-1)
      }
      println(ans)
    } 

  }
}