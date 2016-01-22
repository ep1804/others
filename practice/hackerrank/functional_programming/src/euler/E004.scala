package euler

import scala.collection.Searching._

object LargestPalindromeProduct {
  
  def isPalindrome(n: Int): Boolean = {
    val s = n.toString.toCharArray()
    if(s(0) != s(5) || s(1) != s(4) || s(2) != s(3) ) false else true
  }
  
  val pp = (for{ 
      x <- (100 to 999)
      y <- (100 to 999)
      if x * y >= 100000
      if x * y < 1000000
      if isPalindrome(x * y) 
    } yield (x * y)).sorted
  
  def largestPalProd(limit: Int): Int = {
    val idx = pp.search(limit) match {
      case Found(x) => x
      case InsertionPoint(x) => x - 1
    }
    
    pp(idx)
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    val T = in.next.toInt
    val ps = in.take(T).map(_.toInt)
    
    ps.map(largestPalProd(_)) foreach println
  }
}