package euler

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set

object PandigitalProducts {

  def combi(a: Int, b: Int): Int = {
    if (a == b) return 1
    val b1 = math.min(b, a - b)
    (a to (a - b1 + 1) by -1).toList.foldLeft(1)(_ * _) / (b1 to 1 by -1).toList.foldLeft(1)(_ * _)
  }

  // n-th combination (index start from 0)
  def combination[T](a: Seq[T], n: Int, idx: Int): Seq[T] = {
    if (n < 1 || a.length < n) return Nil

    val res = ListBuffer[T]()
    val b = ListBuffer.concat(a)

    def loop(idx: Int, n1: Int): Unit = {
      if (n1 == 0) return

      if (n1 == 1) {
        res += b(idx.toInt)
        return
      }

      val lim = combi(b.size - 1, n1 - 1)

      if (idx < lim) {
        res += b.remove(0)
        loop(idx, n1 - 1)
      } else {
        b.remove(0)
        loop(idx - lim, n1)
      }
    }

    loop(idx, n)
    res
  }

  def solve(n: Int): Int = {
    val digits = for {
      pd <- 2 to n
      md <- 1 to (pd / 2)
      mr <- md to (pd - md + 1)
      if md + mr == pd || md + mr == pd + 1
      if md + mr + pd == n
    } yield (md, mr, pd)

    //println(digits)

    val ns = (1 to n).toList

    val buf = Set[Int]()

    for ((a, b, c) <- digits) {
      for (i <- 0 until combi(ns.length, a)) {
        val com1 = combination(ns, a, i)

        for (per1 <- com1.permutations) {

          val md = per1.foldLeft(0) { case (a, b) => (a * 10 + b) }

          for (j <- 0 until combi(ns.length - a, b)) {
            val com2 = combination(ns.filter(!com1.contains(_)), b, j)

            for (per2 <- com2.permutations) {
              val mr = per2.foldLeft(0) { case (a, b) => (a * 10 + b) }

              if (md < mr) {

                val spd = (md * mr).toString
                val smd = md.toString
                val smr = mr.toString

                //println(s"md: $per1 $md, mr: $per2 $mr")

                if (spd.forall(_ - '0' <= n) &&
                    spd.length + smd.length + smr.length == n &&
                    (spd + smd + smr + "0").distinct.size == n + 1) {
                  
                  //println(s"$md * $mr == ${md * mr}")

                  buf += md * mr
                }
              }
            }
          }
        }
      }
    }

    buf.sum
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val n = in.next.toInt

    println(solve(n))
  }
}