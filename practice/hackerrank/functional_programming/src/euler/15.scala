package euler

object LatticePaths {
  
  val modulo = 1000000007L
  
  def lattice(n: Int, m: Int): Long = {
    if(n == 1) m + 1
    else if(m == 1) n + 1
    else lattice(n - 1, m) + lattice(n, m - 1)
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val nms = in.take(t).map(_.trim.split("\\s+").map(_.toInt))
    
    nms foreach {case Array(n, m) => println(lattice(n, m) % modulo)}
  }
}