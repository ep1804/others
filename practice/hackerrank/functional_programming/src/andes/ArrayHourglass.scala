package andes

object ArrayHourglass {
  
  val len = 6
  
  def sumHourglass(a: Array[Array[Int]])(r: Int, c: Int): Int = 
    a(r)(c) + a(r)(c+1) + a(r)(c+2) + a(r+1)(c+1) + a(r+2)(c) + a(r+2)(c+1) + a(r+2)(c+2)
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val a = in.take(len).map(x => x.trim.split("\\s+").map(_.toInt).toArray).toArray

    val sumHg = sumHourglass(a)_
    
    val sums = for{
      r <- 0 until (len - 2)
      c <- 0 until (len - 2)      
    } yield sumHg(r, c)
    
    println(sums.max)

  }
}