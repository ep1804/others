package euler

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object DistinctPowers {

  lazy val primes: Stream[Int] =
    2 #:: Stream.from(3, 2).filter(x => primes.takeWhile(_ <= math.sqrt(x)).forall(x % _ != 0))

  def factorize(n: Int): Seq[(Int, Int)] = {

    def divRep(n: Int, d: Int): (Int, Int) = {
      @tailrec
      def loop(n: Int, count: Int): (Int, Int) = {
        if (n % d != 0) (n, count)
        else loop(n / d, count + 1)
      }
      loop(n, 0)
    }

    val res = ListBuffer[(Int, Int)]()

    @tailrec
    def loop(n: Int): Unit = {

      if (n == 1) return

      val ps = primes.takeWhile(_ <= math.sqrt(n)).filter(n % _ == 0) // small prime factors

      if (ps.size == 0) {
        res += ((n, 1))
      } else {
        val next = ps.foldLeft(n) {
          case (x, p) =>
            val (nx, pow) = divRep(x, p)
            res += ((p, pow))
            nx
        }
        loop(next)
      }
    }

    loop(n)
    res
  }

  def gcd(a: Int, b: Int): Int = {
    if (b > a) gcd(b, a)
    else if (b == 0) a
    else gcd(b, a % b)
  }
  
  def isPrime(n: Int): Boolean = primes.takeWhile(_ <= math.sqrt(n)).forall(n % _ != 0)
  
  def solve(N: Int): Long = {

    def distinctPowers(n: Int): Int = {
      
      if(isPrime(n)) return N - 1
      
      /* if n is non-prime,
       * 1) factorize
       * 2) g = gcd of powers of factors
       * 3) additional distinct powers = ... eliminate duplicated ones 
       */
      
      val fs = factorize(n)
      val z = fs(0)._2
      val g = fs.map(_._2).foldLeft(z)(gcd)
      
      if(g == 1) return N - 1
      
      val ps = (2 to N).map(_.toLong * g)
      val NL = N.toLong
      (1 to (g-1)).foldLeft(ps){case (ps, x) => ps.filter(p => p > NL * x || p % x != 0 )}.size
    }
    
    (2 to N).map(distinctPowers(_).toLong).sum
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val n = in.next.toInt

    println(solve(n))
  }
}