object SuperDigit {
  
  def sumDigit(s: String): Long = {    
    def loop(cs: List[Char], acc: Long): Long = cs match {
      case List() => acc
      case x :: xs => loop(xs, acc + x - '0')
    }    
    loop(s.toList, 0)
  }
  
  def superDigit(n: Long): Long = {
    if(n < 10) n
    else superDigit(sumDigit(n.toString))
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val NK = in.next.trim.split("\\s+")
    val N = NK(0)
    val K = NK(1).toInt
    
    println(superDigit(sumDigit(N) * K))    
  }
}