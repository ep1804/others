package euler

object DoubleBasePalindromes {

  implicit class StringExtension(x: String) {
    def isPalindrome(): Boolean = {
      val len = x.length
      (0 until len / 2).toList.forall(i => x.charAt(i) == x.charAt(len - i - 1))
    }
  }

  implicit class IntExtension(x: Int) {
    def toBaseKString(k: Int): String = {
      val sb = new StringBuilder
      def loop(n: Int): Unit = {
        if (n == 0) return
        sb.append(n % k)
        loop(n / k)
      }
      loop(x)
      sb.reverse.toString
    }
  }
  
  def solve(n: String, k: String): Long = {
    
    var sum = 0L
    
    for(i <- 1 until n.toInt){
      if(i.toString.isPalindrome() && i.toBaseKString(k.toInt).isPalindrome())
        sum += i
    }
    
    sum
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val Array(n, k) = in.next.trim.split("\\s+")

    println(solve(n, k))
  }
}