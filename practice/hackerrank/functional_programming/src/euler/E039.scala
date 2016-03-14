package euler

object IntegerRightTriangles {

  val r2 = math.sqrt(2) + 1
  
  def rightTriangle(p: Long): Int = {
    (for{
      a <- (1L to (p / r2).toInt)
      b <- (a + 1L to ((p - a) / 2))
      if a * a + b * b == (p - a - b) * (p - a - b)
    } yield 1).size
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.trim.toInt
    val ns = in.take(t).map(_.trim.toInt).toList
    
    // build cache
    val cache1 = ((1L to ns.max) map rightTriangle)
    val cache2 = collection.mutable.ArrayBuffer[Int](1)
    
    var max = 0
    var maxP = 1
    for(i <- 1 until cache1.size){
      if(cache1(i) > max){
        max = cache1(i)
        maxP = i + 1
      }
      cache2 += maxP
    }
    
//    println(rightTriangle(120))
//    println(cache1.toList)
//    println(cache2.toList)
    
    ns foreach (n => println(cache2(n - 1)))
  }
}