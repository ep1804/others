package euler

object EvenFibonacci {
  val in = io.Source.stdin.getLines
  val T = in.next.toInt
  val ns = in.take(T).map(_.toLong)
  
  println(Long.MaxValue)
}