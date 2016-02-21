package weekOfCode19

object CoolguyAndTwoSubsequences2 {
  val mod = 1000000007

  def solve(n: Int, as: Array[Int]): Int = {

    def f(a: Int, b: Int): Int = as.drop(a - 1).take(b - a + 1).min

    val ans = for {
      a <- (1 to n)
      b <- (a to n)
      c <- ((b + 1) to n)
      d <- (c to n)
    } yield math.min(f(a, b), f(c, d))

    ans.sum % mod
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val n = in.next.toInt
    val as = in.next.trim.split(" ").map(_.toInt)

    println(solve(n, as))
  }

}