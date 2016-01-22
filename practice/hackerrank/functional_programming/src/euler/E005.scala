package euler

object SmallestMultiple {

  def gcd(a: Int, b: Int): Int = 
    if(b == 0) a 
    else if(a > b) gcd(b, a % b)
    else gcd(a, b % a)
  
  def lcm(a: Int, b: Int): Int = a * b / gcd(a, b)

  def solve(n: Int): Int = (1 to n).toList.foldLeft(1)(lcm)
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    val T = in.next.toInt
    val ns = in.take(T).map(_.toInt)
    
    ns map(solve(_)) foreach println
  }
}