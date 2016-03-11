package euler

object E034 {

  lazy val fac: Stream[Int] = 1 #:: 1 #:: (Stream.from(2) zip fac.tail).map { case (a, b) => a * b }
  val factorial = fac.take(10).toIndexedSeq.map(_.toLong)

  lazy val curious: Stream[Int] = Stream.from(10).filter(x => x.toString.map(c => factorial(c - '0')).sum % x == 0)

  def solve(n: Int): Int = {
    curious.takeWhile(_ <= n).toList.sum
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val n = in.next.toInt

    println(solve(n))
  }
}