package euler

object E025 {

  lazy val fib: Stream[BigInt] =
    1 #:: 1 #:: (fib zip fib.tail).map { case (a, b) => (a + b) }

  // fib digits and index
  lazy val fib2 =
    (fib zip Stream.from(1)).map { case (a, b) => (a.toString.length, b) }

  lazy val fib3: Stream[(Int, Int)] =
    fib2(0) #:: (fib2 zip fib2.tail).filter { case ((a, _), (b, _)) => a != b }.map { case (a, b) => b }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ns = in.take(t).map(_.toInt)

    ns foreach { n => println(fib3(n)) }
    
    println(fib3.take(5000).mkString(" "))

    /*
    println(fib.take(50).mkString(" "))
    println(fib2.take(50).mkString(" "))
    println(fib3.take(5).mkString(" "))
    */
  }
}