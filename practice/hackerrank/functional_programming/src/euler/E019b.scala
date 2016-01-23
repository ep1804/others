package euler

import java.util.GregorianCalendar
import java.util.Calendar

object CountingSundays3 {

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
      } yield new GregorianCalendar(y.toInt, m - 1, 1)                  // BEWARE m - 1

      month1days.map { _.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY }.count(_ == true)
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