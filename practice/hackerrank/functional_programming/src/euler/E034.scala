package euler

object DigitFactorials {

  lazy val fac: Stream[Int] = 1 #:: 1 #:: (Stream.from(2) zip fac.tail).map { case (a, b) => a * b }
  val factorial = fac.take(10).toArray

  implicit class IntExtension(x: Int) {
    def isCurious(): Boolean = {
      x.toString().map(c => factorial(c - '0')).sum % x == 0
    }
  }

  def solve(n: Int): Long = {
    var sum: Long = 0
    for (x <- (10 until n)) {
      if(x.isCurious()) sum += x
    }
    sum
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val n = in.next.toInt

    println(solve(n))
  }
}