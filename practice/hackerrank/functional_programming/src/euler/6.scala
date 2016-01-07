package euler

object SumSquareDifference {
  
  lazy val sq0: Stream[Long] = Stream.from(1).map(x => (x * x).toLong)
  lazy val sumSq: Stream[Long] = 1L #:: sumSq.zip(sq0.tail).map{case (a, b) => a + b}
  
  lazy val sqSum: Stream[Long] = Stream.from(1).map(x => {val y = x.toLong; val s = (y * (y + 1) / 2); s * s})
  
  lazy val diff: Stream[Long] = (sumSq zip sqSum).map{case (a, b) => b - a}   
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    val T = in.next.toInt
    val ns = in.take(T).map(_.toInt)
    
    //println(sq0.take(5).toList)
    //println(sumSq.take(5).toList)
    //println(sqSum.take(5).toList)
    //println(diff.take(5).toList)
    
    ns foreach(n => println(diff(n-1))) 
  }
}