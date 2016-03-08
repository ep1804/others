package andes

object T0102 {

  def solve(n: Int, k: Int, l: Int, m: Int, s: String): Int = {
    val occurences = for {
      len <- k to l
      subs <- s.sliding(len).toList.distinct
      if subs.distinct.length <= m
    } yield subs.r.findAllIn(s).length

    occurences.max
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val n = in.next.trim.toInt
    val Array(k, l, m) = in.next.trim.split("\\s+").map(_.toInt)
    val s = in.next

    println(solve(n, k, l, m, s))
  }
}