package euler

import scala.collection.mutable.ListBuffer
import scala.annotation.tailrec

object HighlyDivisibleTriangularNumber {

  def from(start: Long): Stream[Long] = start #:: from(start + 1)
  //lazy val from2: Stream[Long] = 2 #:: from2.map(_ + 1)

  lazy val primes: Stream[Int] =
    2 #:: Stream.from(3, 2).filter(x => primes.takeWhile(_ <= math.sqrt(x)).forall(x % _ != 0))

  lazy val triangles: Stream[Long] =
    1L #:: (from(2L) zip triangles).map { case (a, b) => a + b }

  // returned pairs: (prime factor, power)
  def factorize(n: Long): ListBuffer[(Long, Int)] = {

    // collection buffer
    val facs = ListBuffer[(Long, Int)]()

    // repeat division by p until there's no p factor in n
    // returned pair denotes: (quotient, number of repeated division by p)
    @tailrec
    def divRep(n: Long, p: Long, acc: Int = 0): (Long, Int) =
      if (n % p != 0) (n, acc)
      else divRep(n / p, p, acc + 1)

    @tailrec
    def loop(n: Long): Unit = {

      // if prime, add n to divisors
      val ps = primes.takeWhile(_ <= math.sqrt(n)).filter(n % _ == 0) // small prime factors      
      if (ps.size == 0) { // n is prime
        facs += ((n, 1))
        return
      }

      // remove small prime factors from n
      val n2 = ps.foldLeft(n) { (quo, pri) =>
        val (quo2, pow) = divRep(quo, pri)
        facs += ((pri, pow))
        quo2
      }

      if (n2 == 1) return
      else loop(n2)
    }

    if (n != 1) loop(n)
    facs
  }

  def numberOfFactors(n: Long): Int = {
    val facs = factorize(n)
    facs.map { case (f, p) => p + 1 }.foldLeft(1)(_ * _)
  }

  // triangle number paired with its number of factors
  lazy val tf: Stream[(Long, Int)] = triangles.map(t => (t, numberOfFactors(t)))

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ns = in.take(t).map(_.toInt)

    /*
    println("Primes: " + primes.take(10).toList)
    println("Triangles: " + triangles.take(10).toList)
    val test = 2L * 2 * 5 * 5 * 5 * 7 * 7 * 23 * 23 * 29
    println("Factorization of " + test + ": " + factorize(test))
    println("Factorization of 1: " + factorize(1L))
    println("Triangles with its number of factors: " + tf.take(10).toList)
    */

    ns foreach (n => println(tf.find { case (t, f) => f > n }.get._1))
  }
}