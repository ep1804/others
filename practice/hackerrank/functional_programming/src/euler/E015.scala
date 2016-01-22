package euler

import scala.collection.mutable.Map

object LatticePaths {
  
  val modulo = 1000000007L
  
  val latticeBuf = Map[(Int, Int), BigInt]()
  
  // n <= m
  def lattice(n: Int, m: Int): BigInt = {
    if(n == 1) m + 1
    else if(latticeBuf.contains((n, m)))
        latticeBuf((n, m))
    else {
      val res = if (n == m) 2 * lattice(n - 1, m) else lattice(n - 1, m) + lattice(n, m - 1)
      latticeBuf.put((n, m), res)
      res
    }
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.trim.toInt
    val nms = in.take(t).map(_.trim.split("\\s+").map(_.toInt).sorted)
    
    nms foreach {case Array(n, m) => println(lattice(n, m) % modulo)}
  }
}