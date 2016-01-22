package euler

object EvenFibonacci {

  // Ref: http://www.scala-lang.org/api/current/index.html#scala.collection.immutable.Stream
  lazy val fibs: Stream[Long] = 1 #:: 2 #:: fibs.zip(fibs.tail).map { n => n._1 + n._2 }

  def sumFib(n: Long): BigInt =
    fibs.takeWhile(_ <= n).filter(_ % 2 == 0).foldLeft(BigInt(0))((a, b) => a + BigInt(b))
  // Note: you cannot NOT change the takeWhile to filter.

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val T = in.next.toInt
    val ns = in.take(T).map(_.toLong)

    ns foreach { n => println(sumFib(n).toString()) }
  }
}