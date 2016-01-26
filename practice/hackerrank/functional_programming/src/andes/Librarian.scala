package andes

object Librarian {

  def fine(d: Seq[Int], d1: Seq[Int]): Int = (d, d1) match {
    case (Seq(d, m, y), Seq(d1, m1, y1)) => {
      if (y1 > y) 10000
      else if (y1 == y && m1 > m) (m1 - m) * 500
      else if (y1 == y && m1 == m && d1 > d) (d1 - d) * 15
      else 0
    }
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val d1 = in.next.split(" ").map(_.toInt)
    val d = in.next.split(" ").map(_.toInt)

    println(fine(d, d1))
  }
}