package euler

object NDigitFibonacciNumber {

  val map = collection.mutable.Map[Int, Int]((1, 1))
  
  def buildMap(maxDigit: Int = 5000) = {    
    def loop(f1: BigInt, f2: BigInt, f2Digit: Int, f3Idx: Int): Unit = {
      val f3 = f1 + f2
      val f3Digit = f3.toString.length
      if (f3Digit > maxDigit) Unit
      else {
        if(f3Digit > f2Digit) map.put(f3Digit, f3Idx)        
        loop(f2, f3, f3Digit, f3Idx + 1)
      }
    }    
    loop(1, 1, 1, 3)
  }
  
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ns = in.take(t).map(_.toInt)

    buildMap()
  
    ns foreach { n => println(map(n)) }
  }
}