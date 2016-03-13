package euler

object TruncatablePrimes {

  lazy val primes: Stream[Int] =
    2 #:: Stream.from(3, 2).filter(x => primes.takeWhile(_ <= math.sqrt(x)).forall(x % _ != 0))

  val cache = collection.mutable.Map[Int, Boolean]()

  implicit class IntExtension(x: Int) {

    def isTruncationPrime(): Boolean = {
      val s = x.toString
      for (i <- (1 until s.length)) {
        if (!s.take(i).toInt.isPrime() || !s.drop(i).toInt.isPrime())
          return false
      }
      true
    }

    def isPrime(): Boolean = {
      if (x == 0 || x == 1) return false
      if (cache.contains(x)) return cache(x)
      val res = primes.takeWhile(_ <= math.sqrt(x)).forall(x % _ != 0)
      cache += (x -> res)
      res
    }
  }

  def solve(n: Int): Long = {
    var sum: Long = 0
    for (k <- (11 until n)) {
      if (k.isPrime() && k.isTruncationPrime()) sum += k
    }
    sum
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val n = in.next.toInt

    println(solve(n))
  }
}