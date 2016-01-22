package euler

object CountingSundays {
  
  val mLen     = Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
  val mLenLeap = Array(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
  
  def leap(y: Long): Boolean = 
    if((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) true else false
  
  // From each month from 1900, compute days in month
  val m = (1900 to (2900 + 400 * 7)).flatMap(y => if(leap(y)) mLenLeap else mLen)
  
  // For each 1st days of every months from 1900, 
  // compute number of days from Dec. 31, 1899 (Sunday)
  lazy val mCum: Stream[Long] = 1 #:: (mCum zip m).map{ case (a, b) => a + b }
  
  // For each 1st days of every months from 1900, 
  // compute date (Sunday = 0, Monday = 1, and so on)
  val mBuf = mCum.take((1001 + 400 * 7) * 12).map(_ % 7).toVector
  
  def sundays(d: Tuple2[Tuple3[Long, Long, Long], Tuple3[Long, Long, Long]]): Int = d match {
    case ((yo1, m1, d1), (yo2, m2, d2)) => {
      val y1 = 1900 + (yo1 - 1900) % (400 * 7)
      val y2 = y1 + (yo2 - yo1)
      
      val idx1 = ((y1 - 1900) * 12 + (m1 - 1) + (if(d1 > 1) 1 else 0)).toInt
      val idx2 = ((y2 - 1900) * 12 + (m2 - 1) + 1).toInt
      
      mBuf.drop(idx1).take(idx2 - idx1).count(_ == 0)
    }
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ds = in.take(2 * t).
      map { s => val ns = s.split("\\s+").map(_.toLong); (ns(0), ns(1), ns(2)) }.
      grouped(2).map { case Seq(a, b) => (a, b) }
    
    ds foreach (d => println(sundays(d)))
  }
}