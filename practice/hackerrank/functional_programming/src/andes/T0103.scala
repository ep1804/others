package andes

object T0103 {

  def StringSimilarity(inputs: Array[String]): Array[Int] = {
    val buf = collection.mutable.ListBuffer[Int]()

    for (s <- inputs) {

      val starting = 0.until(s.length).filter(x => s.charAt(x) == s.head)
      var sum = 0

      // from the starting positions, proceed sub strings and measure length
      import scala.annotation.tailrec
      @tailrec
      def loop(pos: Seq[Int], cur: Int): Unit = {
        if (pos.length == 0) return
        sum += pos.length
        val nextPos = pos.filter(x => x + 1 < s.length && s.charAt(x + 1) == s.charAt(cur + 1)).map(_ + 1)
        loop(nextPos, cur + 1)
      }

      loop(starting, 0)

      buf += sum
    }

    buf.toArray
  }

  def main(args: Array[String]): Unit = {
    println(StringSimilarity(Array("ababaa", "aa")).toList)
  }

}