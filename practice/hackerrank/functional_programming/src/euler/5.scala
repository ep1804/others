package euler

object SmallestMultiple {
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    val T = in.next.toInt
    val ns = in.take(T).map(_.toInt)
    
    ns foreach println
  }
}