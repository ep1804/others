package euler

object CountingSundays {
  
  val mLen = Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
  
  val mLenLeap = Array(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
  
  def leap(y: Long): Boolean = if((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) true else false
  
  // Days in Month from Jan 1 1900
  val year1400 = (1900 to 3300).flatMap(y => if(leap(y)) mLenLeap else mLen)
  
  // Days in month from 
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ds = in.take(2 * t).
      map { s => val ns = s.split("\\s+").map(_.toLong); (ns(0), ns(1), ns(2)) }.
      grouped(2).map { case Seq(a, b) => (a, b) }

    ds foreach println
  }
}