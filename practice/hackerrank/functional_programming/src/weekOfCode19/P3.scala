package weekOfCode19

object ScalarProducts {

  def solve(c: Int, m: Int, n: Int): Int = {

    if (n < 2) return 0

    lazy val as: Stream[Long] = 
      0L #:: c.toLong #:: (as zip as.tail).map { case (a, b) => ((a + b) % m) }

    val a = as.drop(2).take(2 * n).toArray

    def prod(i: Int, j: Int): Int = 
      ((a(2 * i) * a(2 * j) + a(2 * i + 1) * a(2 * j + 1)) % m).toInt

    val prods = for {
      i <- (0 until n)
      j <- ((i + 1) until n)
    } yield prod(i, j)

    prods.distinct.size % m
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val Array(c, m, n) = in.next.trim.split(" ").map(_.toInt)

    println(solve(c, m, n))
  }
}