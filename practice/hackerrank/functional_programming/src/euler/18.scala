package euler

object MaximumPathSum1 {

  def maxPath(ps: Iterator[List[Int]]) = {
    val sums = ps.foldLeft(List(0)) {
      case (xs, ys) =>
        (((0 :: xs) zip (xs :+ 0)).map { case (a, b) => math.max(a, b) } zip ys).
          map { case (a, b) => a + b }
    }
    sums.max
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    for (i <- 1 to t) {
      val n = in.next.toInt
      val paths = in.take(n).map { s => s.trim.split("\\s+").map(_.toInt).toList }

      println(maxPath(paths))
    }
  }
}