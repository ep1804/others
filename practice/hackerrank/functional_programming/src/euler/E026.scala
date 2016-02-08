package euler

object ReciprocalCycles  {
  
  def cycleLen(n: Int): Int = {
    
    val rs = collection.mutable.Map[Int,Int]()
    
    // proceed to next digits, compute remainder, check if the remainder is zero or repeated
    def loop(a: Int, b: Int, count: Int): Int = {
      if(a == 0) return 0

      if(rs.contains(a)) return count - rs(a)
      else rs += ((a, count))

      loop((a % b) * 10, b, count + 1)
    }

    loop(1, n, 0)
  }
  
  val cls = (1 to 10000).map(cycleLen(_))
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ns = in.take(t).map(_.toInt)
  
    val cache = Array.ofDim[Int](10000)
    var max = -1;
    var idx = -1;
    
    for(i <- 0 until 10000){
      if(cls(i) > max){
        max = cls(i)
        idx = i
      }
      cache(i) = idx + 1
    }
    
    ns foreach {n => println(cache(n - 2))}
  }
}