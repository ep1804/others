package euler

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set

object DigitCancelingFractions {

  def cancelables(lim: Map[Char, Int], k: Int): Set[Map[Char, Int]] = {

    val buf = Set[Map[Char, Int]]()

    def loop(lim: Map[Char, Int], k: Int, acc: Map[Char, Int]): Unit = {
      if (k == 0) {
        buf += acc
        return
      }
      for ((ch, n) <- lim) {
        if (n > 1) {
          if (acc.contains(ch)) {
            loop((lim - ch) + (ch -> (n - 1)), k - 1, (acc - ch) + (ch -> (acc(ch) + 1)))
          } else {
            loop((lim - ch) + (ch -> (n - 1)), k - 1, acc + (ch -> 1))
          }
        } else {
          if (acc.contains(ch)) {
            loop((lim - ch), k - 1, (acc - ch) + (ch -> (acc(ch) + 1)))
          } else {
            loop((lim - ch), k - 1, acc + (ch -> 1))
          }
        }
      }
    }

    loop(lim, k, Map[Char, Int]())
    buf
  }

  def solve(digit: Int, k: Int): Seq[Int] = {

    val nuBuf = Set[Int]()
    val deBuf = Set[Int]()

    def check(a: Int, b: Int): Boolean = {

      val a1 = a.toString
      val b1 = b.toString

      val a2 = a1.zipWithIndex
      val b2 = b1.zipWithIndex

      val a3 = a2.filter(x => x._1 != '0' && b1.contains(x._1))
      if (a3.length < k) return false

      val b3 = b2.filter(x => x._1 != '0' && a1.contains(x._1))
      if (b3.length < k) return false

      val a4 = a3.groupBy(_._1).map { case (x, y) => (x, y.map(_._2)) }
      val b4 = b3.groupBy(_._1).map { case (x, y) => (x, y.map(_._2)) }

      val lim = a4.keys.map(k => (k, math.min(a4(k).size, b4(k).size))).toMap
      if (lim.map(_._2).sum < k) return false

      val cancelables1 = cancelables(lim, k)

      // cancelation cases. 
      // each tuple denotes: (indices of canceled digits in number a, indices of canceled digits in number b) 
      val cases = (for { cb <- cancelables1 } yield cb.map {
        case (k, v) => (k, for {
          a <- a4(k).combinations(v)
          b <- b4(k).combinations(v)
        } yield (a, b))
      }.values.foldLeft(Iterator((IndexedSeq[Int](), IndexedSeq[Int]()))) {
        case (xs, ys) => for {
          (xa, xb) <- xs
          (ya, yb) <- ys
        } yield (xa ++ ya, xb ++ yb)
      }).flatten

      // canceled numbers
      val cs = cases.map { cs =>
        (a2.filter(x => !cs._1.contains(x._2)).map(_._1).mkString("").toInt,
          b2.filter(x => !cs._2.contains(x._2)).map(_._1).mkString("").toInt)
      }

      !cs.forall { case (ac, bc) => a.toDouble / b != ac.toDouble / bc }
    }
    
//    println(check(4808, 8414))
//    println(check(3016, 6032))

    val start = math.pow(10, digit - 1).toInt
    val end = math.pow(10, digit).toInt - 1

    val originals = for {
      a <- start to end
      b <- (a + 1) to end
      if check(a, b)
    } yield (a, b)
    
    originals foreach println

    val nd = originals.unzip
    List(nd._1.sum, nd._2.sum)
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val Array(n, k) = in.next.trim.split(" ").map(_.toInt)

    println(solve(n, k).mkString(" "))
  }
}