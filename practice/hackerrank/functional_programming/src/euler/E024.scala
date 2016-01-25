package euler

object LexicographicPermutations {

  // c.f.
  //def from(start: Long, jump: Long): Stream[Long] = start #:: from(start + jump, jump)

  lazy val factorial: Stream[Long] =
    1 #:: 1 #:: (factorial.tail zip Stream.from(2)).map { case (a, b) => a * b }

  def first(s: String, n: Long): Int = ((n - 1) / factorial(s.length() - 1)).toInt

  def remove(s: String, i: Int): String = s.take(i) + s.drop(i + 1).take(s.length() - i - 1)

  def permut(s: String, n: Long): String = {

    val sb = new StringBuilder()

    def loop(s: String, n: Long): String = {
      if (n == 1) return s
      val fst = first(s, n)
      sb += s.charAt(fst)
      return loop(remove(s, fst), n - factorial(s.length() - 1) * fst)
    }

    val sr = loop(s, n)

    sb.append(sr).toString
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ns = in.take(t).map(_.toLong)

    val s = "abcdefghijklm"

    ns foreach { n => println(permut(s, n)) }
  }
}