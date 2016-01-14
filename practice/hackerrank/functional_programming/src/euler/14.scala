package euler

object IntestCollatzSequence {
  
  // length of collatz sequence starting from n
  def collatz(n: Int): Int = {
    def loop(x: Int, acc: Int = 1): Int = {
      if(x == 1) acc
      else if(x % 2 == 0) loop(x / 2, acc + 1)
      else loop(3 * x + 1, acc + 1)
    }
    loop(n.toInt)
  }

  // infinite series of collatz(n)
  lazy val cs = Stream.from(1) map collatz
  
  // infinite series of tuples that denotes: (max collatx(n) until n, max index)  
  lazy val mcs: Stream[(Int, Int)] = 
    (1, 0) #:: (mcs zip cs.zipWithIndex.tail).map{ case((m, mi), (c, ci)) => if(m > c) (m, mi) else (c, ci)}
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.trim.toInt
    val ns = in.take(t).map(_.trim.toInt)    
    
    println("Collatz length series upto 15th: " + cs.take(15).toList)
    println("Max collatz length value and index series upto 15th: " + mcs.take(15).toList)
    
    ns foreach {n => println(mcs(n)._2 + 1)}
  }
}