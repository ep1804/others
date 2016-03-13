package euler

object PandigitalMultiples {

  implicit class IntExtension(x: Int) {
    def isPandigitalMultiple(k: Int): Boolean = {
      var s = x.toString

      for (i <- (2 to 10)) {
        s = s + (x * i).toString
        if (s.length > k) return false
        if (s.length == k && !s.contains('0') && s.forall(c => c - '0' <= k) && s.distinct.length == k) return true
      }
      false
    }
  }

  def solve(n: Int, k: Int): Seq[Int] = {
    (2 until n).filter(_.isPandigitalMultiple(k))
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val Array(n, k) = in.next.trim.split("\\s+").map(_.toInt)

    println(solve(n, k).mkString("\n"))
  }
}