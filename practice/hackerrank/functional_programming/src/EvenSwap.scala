object EvenSwap {
  def main(args: Array[String]) = {
    val in = io.Source.stdin.getLines
    val N = in.next().trim.toInt

    for( s <- in.take(N))
      println(s.toList.grouped(2).flatMap(_.reverse).mkString)
  }
}