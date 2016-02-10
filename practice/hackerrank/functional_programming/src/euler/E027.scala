package euler

object QuadraticPrimes {

  lazy val primes: Stream[Int] =
    2 #:: Stream.from(3, 2).filter(x => primes.takeWhile(_ <= math.sqrt(x)).forall(x % _ != 0))

  def isPrime(n: Int): Boolean = {
    if(n < 2) false
    else primes.takeWhile(_ <= math.sqrt(n)).forall(n % _ != 0)
  }

  def quadPrimeLen(a: Int, b: Int): Int =
    Stream.from(0).takeWhile(n => isPrime(n * n + a * n + b)).size

  def maxLenAB(n: Int): (Int, Int) = {

    // some conditions to reduce test cases    
    // b is prime             ... from n == 0
    // a + b > 0              ... from n == 1
    // 1 + a + b is prime     ... from n == 1
    //  => a + b == 1 or even

    val bs = primes.takeWhile(_ <= n).toList
    val abs = bs.flatMap(b => (-b + 1, b) :: ((-b + 2) to b by 2).map(a => (a, b)).toList)
    
    abs.maxBy { case (a, b) => quadPrimeLen(a, b) }
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val n = in.next.toInt

    val ab = maxLenAB(n) 
    
    println(ab._1 + " " + ab._2)
  }
}