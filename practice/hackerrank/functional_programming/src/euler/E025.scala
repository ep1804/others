package euler

object NDigitFibonacciNumber {
  
  val ¥õ = (1.0 + math.sqrt(5.0)) / 2.0  // golden ratio
  
  // Fibonacci number's length is computed based on closed-form expression of Fibonacci sequence.
  // https://en.wikipedia.org/wiki/Golden_ratio#Relationship_to_Fibonacci_sequence
  // Strictly following the formula, implementation should be as follows:
  // def fd(n: Int) = math.log10((math.pow(¥õ, n) - math.pow(-¥õ, -n))/math.sqrt(5)).toInt + 1
  // But it is very slow when n is large. So, 
  // Approximation is used not using (-¥õ)^n term. 
  // This is ok when n is 2 ~ 5000
  def fd(n: Int) = (n * math.log10(¥õ) - math.log10(5) / 2).toInt + 1

  // (digits, index)
  // (0, 0) is inserted at the first position for convenience of building fdi2 later
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