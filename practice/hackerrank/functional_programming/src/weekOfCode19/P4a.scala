package weekOfCode19

object CoolguyAndTwoSubsequences2 {
  val mod = 1000000007

  def solve(n: Int, as: Array[Int]): Int = {

    def f(a: Int, b: Int): Int = as.drop(a - 1).take(b - a + 1).min


    1
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val n = in.next.toInt
    val as = in.next.trim.split(" ").map(_.toInt)

    println(solve(n, as))
  }

}