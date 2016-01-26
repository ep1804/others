package euler

object NDigitFibonacciNumber {

  // Solution by using closed-form expression of fibonacci sequence
  // https://en.wikipedia.org/wiki/Golden_ratio#Relationship_to_Fibonacci_sequence
  
  val ¥õ = (1.0 + math.sqrt(5.0)) / 2.0  // golden ratio
  
  // fibonacci number digits
  // Approximation not using (-¥õ)^n term. But no problem until digit 5000
  def fd(n: Int) = (n * math.log10(¥õ) - math.log10(5) / 2 + 1).toInt 
  
  // (digits, index)
  lazy val fdi = (0, 0) #:: (1, 1) #:: Stream.from(2).map(x => (fd(x), x))
  
  // series of fibonacci indices where digit changes
  lazy val fdi2: Stream[Int] = 
    (fdi zip fdi.tail).filter { case ((a, _), (b, _)) => a != b }. map { case (a, b) => b._2 }

  val fdi3 = fdi2.take(5000).toArray
    
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ns = in.take(t).map(_.toInt)
   
    ns foreach { n => println(fdi3(n-1)) }
  }
}