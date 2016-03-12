package euler

object CircularPrimes {

  lazy val primes: Stream[Int] =
    2 #:: Stream.from(3, 2).filter(x => primes.takeWhile(_ <= math.sqrt(x)).forall(x % _ != 0))

  val cache = collection.mutable.Map[Int, Boolean]()

  implicit class IntExtension(x: Int) {
    
    def rotations(): Seq[Int] = {
      val s = x.toString
      (0 until (s.length)).map(i => { val (a, b) = s.splitAt(i); (b + a).toInt })
    }
    
    def isPrime(): Boolean = {
      if (cache.contains(x)) return cache(x)
      val res = primes.takeWhile(_ <= math.sqrt(x)).forall(x % _ != 0)
      cache += (x -> res)
      res
    }
  }

  def solve(n: Int): Long = {
    var sum: Long = 0
    for (k <- (2 until n)) {
      if(k.rotations().forall(_.isPrime())) sum += k
    }
    sum
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val n = in.next.toInt

    println(solve(n))
  }
}