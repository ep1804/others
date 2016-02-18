package euler

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object PandigitalProducts {

  def solve(n: Int): Int = {
    
    val d12 = n / 2 + 1
    
    println((1 to n).toList.permutations.next)
     
    1 
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val n = in.next.toInt

    println(solve(n))

  }
}