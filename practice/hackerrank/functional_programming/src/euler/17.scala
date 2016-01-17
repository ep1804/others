package euler

import scala.collection.mutable.ListBuffer

object NumberToWords {

  val zer = "Zero"
  val u20 = Array("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                  "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", 
                  "Eighteen", "Nineteen")
  val tens = Array("Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")
  val hun = "Hundred"
  val level = Array("Thousand", "Million", "Billion")

  def under1000(n: Int): List[String] = {
    val d3 = n / 100
    val d21 = n % 100
    val d2 = d21 / 10
    val d1 = d21 % 10

    val buf = ListBuffer[String]()

    if (d3 > 0) buf += u20(d3 - 1) += hun

    if (d21 == 0) Unit
    else if (d21 < 20) buf += u20(d21 - 1)
    else {
      buf += tens(d2 - 2)
      if (d1 > 0) buf += u20(d1 - 1)
    }

    buf.toList
  }

  def word(n: Long): String = {
    val buf = ListBuffer[String]()

    def loop(x: Long, lv: Int): Unit = {
      if (x == 0) return

      val xq = x / 1000
      val xr = (x % 1000).toInt

      val ws = under1000(xr)

      if (!ws.isEmpty) {
        if (lv > 0) level(lv - 1) +=: buf
        ws ++=: buf
      }

      loop(xq, lv + 1)
    }

    loop(n, 0)

    if (buf.isEmpty) zer
    else buf mkString " "
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ns = in.take(t).map(_.toLong)

    ns foreach { n => println(word(n)) }
  }
}