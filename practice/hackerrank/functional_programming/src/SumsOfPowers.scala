object SumsOfPowers {
  
  def numberOfWays(x: Int, n: Int): Int = {
    
    def loop(sum: Int, max: Int): Int = {      
      if(sum == x) 1
      else if(sum > x) 0
      else (((max + 1) to math.pow(x - sum, 1.0/n).toInt + 1) map {k => loop(sum + math.pow(k, n).toInt, k)}) .sum
    }

    loop(0, 0)
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val X = in.next.trim.toInt
    val N = in.next.trim.toInt

    println(numberOfWays(X, N))    
  }
}