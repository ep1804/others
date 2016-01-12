package euler

object LargestProductInGrid {

  def maxMult(a: Array[Array[Int]]): Int = {

    def multCol(r: Int, c: Int): Int =
      a(r)(c) * a(r + 1)(c) * a(r + 2)(c) * a(r + 3)(c)

    def multRow(r: Int, c: Int): Int =
      a(r)(c) * a(r)(c + 1) * a(r)(c + 2) * a(r)(c + 3)

    def multDia(r: Int, c: Int): Int =
      math.max(a(r)(c) * a(r + 1)(c + 1) * a(r + 2)(c + 2) * a(r + 3)(c + 3),
               a(r + 3)(c) * a(r + 2)(c + 1) * a(r + 1)(c + 2) * a(r)(c + 3))

    val colMax = (for {
      r <- 0 to 16
      c <- 0 to 19
    } yield multCol(r, c)).max

    val rowMax = (for {
      r <- 0 to 19
      c <- 0 to 16
    } yield multRow(r, c)).max

    val diaMax = (for {
      r <- 0 to 16
      c <- 0 to 16
    } yield multDia(r, c)).max

    math.max(math.max(colMax, rowMax), diaMax)
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val a = in.take(20).map { x => x.split("\\s+").map(_.toInt).toArray }.toArray

    println(maxMult(a))
  }
}