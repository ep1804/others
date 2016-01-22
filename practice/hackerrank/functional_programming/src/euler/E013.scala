package euler

object LargeSum {
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines    
    val t = in.next.toInt
    val ns = in.take(t).map(BigInt(_))
    
    val sum = ns.foldLeft(BigInt(0))(_ + _)
    
    println(sum.toString.take(10))
  }
}