package euler

object Multiple35 {
  
  def sumTo(n: Long): Long = n * (n + 1) / 2
  
  def sum35(n: Long): Long = 3 * sumTo(n / 3) + 5 * sumTo(n / 5) - 15 * sumTo(n / 15)
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    val T = in.next.toInt
    val Ns = in.take(T).map(_.toLong)
  
    for(n <- Ns) { println(sum35(n - 1)) }    
  }
}