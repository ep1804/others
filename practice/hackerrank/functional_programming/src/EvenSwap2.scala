import scala.annotation.tailrec

object EvenSwap2 {
  
  def evenSwap(s: String) = {
  
    @tailrec
    def evenSwap(acc: StringBuilder, s: List[Char]): StringBuilder = s match {
      case x :: y :: xs => evenSwap(acc append y append x, xs)
      case _ => acc
    }
    
    evenSwap(new StringBuilder(), s.toList).toString 
  }
  
  def main(args: Array[String]) = {
    val in = io.Source.stdin.getLines
    
    val N = in.next().trim.toInt
    
    for(s <- in.take(N))
      println(evenSwap(s))
  }
}