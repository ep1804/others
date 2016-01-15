package euler

object IntestCollatzSequence {

  val fin = 5000000

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.trim.toInt
    val ns = in.take(t).map(_.trim.toInt)

    val cs = Array.ofDim[Int](fin)

    for (i <- 1 to fin) {
      var x: Long = i
      var acc = 1

      while (x != 1) {
        if (x < i) {
          acc = acc + cs((x - 1).toInt) - 1
          x = 1
        } else if (x % 2 == 0) {
          x = x / 2
          acc = acc + 1
        } else {
          x = 3 * x + 1
          acc = acc + 1
        }
      }
      cs(i-1) = acc
    }

    val mcIdxs = Array.ofDim[Int](fin) // max collatz number's index series

    var max = 0
    var maxIdx = 0
    
    for(i <- 0 until fin){
      if(cs(i) >= max){
        max = cs(i)
        maxIdx = i
      }
      mcIdxs(i) = maxIdx      
    }
      
    ns foreach { n => println(mcIdxs(n-1) + 1) }
  }
}