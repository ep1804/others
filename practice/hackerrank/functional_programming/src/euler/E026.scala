package euler

object E026 {
  
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ns = in.take(t).map(_.toInt)
    
    ns foreach println
  }
}