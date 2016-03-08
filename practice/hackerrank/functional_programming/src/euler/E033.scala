package euler

object DigitCancelingFractions {

  var cache1: IndexedSeq[String] = null // for Int to String conversion
  var cache2: Map[String, Int] = null // for String to Int conversion

  implicit class IntExtension(x: Int) {
    def toStringFast: String = cache1(x)
  }

  implicit class StringExtension(x: String) {

    def toIntFast: Int = cache2(x.dropWhile(_ == '0'))

    // different from filter in that one character in s is used to delete only one character in x
    def remove(s: String): String = {
      def loop(rm: String, acc: String): String = {
        if (rm.size == 0) return acc
        else {
          val c = rm(0)
          val s1 = acc.takeWhile(_ != c)
          loop(rm.drop(1), s1 + acc.drop(s1.size + 1))
        }
      }
      loop(s, x)
    }

    // check if order of characters in s is same in x
    def checkOrder(s: String): Boolean = {
      var i = 0
      for (c <- s) {
        i = x.indexOf(c, i) + 1
        if (i == 0) return false
      }
      true
    }

    // different from built-in 'combinations' in that this preserves source order
    def combinationsInOrder(n: Int): Iterator[String] = {
      x.zipWithIndex.combinations(n).map(xs => xs.map(_._1).mkString)
    }

    def repeat(n: Int): String = (1 to n).toList.map(_ => "0").mkString

  }

  // n1 / d1 == n2 / d2
  def solve(digit: Int, k: Int): Seq[Int] = {

    val start = math.pow(10, digit - 1).toInt
    val end = math.pow(10, digit).toInt - 1

    val d2end = math.pow(10, digit - k).toInt - 1

    val fractions = for {
      n1 <- start to (end - 1)
      val n1str = n1.toStringFast

      n2str <- n1str.combinationsInOrder(digit - k) if !n2str.forall(_ == '0')

      val nCancel = n1str.remove(n2str)
      if !nCancel.contains('0')

      val n2 = n2str.toIntFast

      d2 <- (n2 + 1) to math.min(end * n2 / n1, d2end) 
      if (n1 * d2) % n2 == 0

      val d1 = n1 * d2 / n2

      val d1str = d1.toStringFast
      val d2str = d2.toStringFast
      val d2strZeroPadded = "0".repeat(digit - k - d2str.length) + d2str // consider case 1083/1805 = 3/5

      if (nCancel + d2strZeroPadded).sorted == d1str.sorted

      if d1str.checkOrder(d2strZeroPadded) // consider case 1040/1508 = 40/58
    } yield (n1, d1)

    //fractions.distinct.sortBy(_._1) foreach println

    val nd = fractions.distinct.unzip
    List(nd._1.sum, nd._2.sum)
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val Array(n, k) = in.next.trim.split(" ").map(_.toInt)

    cache1 = (0 to math.pow(10, n).toInt - 1).map(_.toString)
    cache2 = cache1.zipWithIndex.toMap

    println(solve(n, k).mkString(" "))
  }
}