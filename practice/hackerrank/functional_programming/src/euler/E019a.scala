package euler

object CountingSundays2 {

  // Zeller's congruence: https://en.wikipedia.org/wiki/Zeller%27s_congruence
  // return: 0 = Saturday, 1 = Sunday, 2 = Monday, ..., 6 = Friday
  def zeller(y: Long, m: Int, d: Int, calendar: String = "Gregorian"): Int = {
    val y1 = if (m < 3) y - 1 else y
    val m1 = if (m < 3) m + 12 else m
    val K = (y1 % 100).toInt
    val J = (y1 / 100).toInt
    if (calendar == "Gregorian")
      (d + 13 * (m1 + 1) / 5 + K + K / 4 + J / 4 + 5 * J) % 7
    else
      (d + 13 * (m1 + 1) / 5 + K + K / 4 + 5 * J) % 7
  }

  def le(y1: Long, m1: Int, d1: Int, y2: Long, m2: Int, d2: Int): Boolean = {
    if (y1 < y2) true
    else if (y1 > y2) false
    else {
      if (m1 < m2) true
      else if (m1 > m2) false
      else d1 <= d2
    }
  }

  def sundays(d: Tuple2[Tuple3[Long, Int, Int], Tuple3[Long, Int, Int]]): Int = d match {
    case ((yo1, m1, d1), (yo2, m2, d2)) => {
      val y1 = 1900 + (yo1 - 1900) % (400 * 7)
      val y2 = y1 + (yo2 - yo1)

      val month1days = for {
        y <- (y1 to y2)
        m <- (1 to 12)
        if (le(y1, m1, d1, y, m, 1) && le(y, m, 1, y2, m2, d2))
      } yield (y, m, 1)

      month1days.map { case (y, m, d) => zeller(y, m, d) }.count(_ == 1)
    }
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ds = in.take(2 * t).
      map { s => val ns = s.split("\\s+").map(_.toLong); (ns(0), ns(1).toInt, ns(2).toInt) }.
      grouped(2).map { case Seq(a, b) => (a, b) }

    ds foreach (d => println(sundays(d)))
  }
}