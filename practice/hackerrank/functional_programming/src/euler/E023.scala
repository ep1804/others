package euler

object NonAbundantSums {
  
  lazy val primes: Stream[Int] =
    2 #:: Stream.from(3, 2).filter(x => primes.takeWhile(_ <= math.sqrt(x)).forall(x % _ != 0))

  // return Seq(prime factors, power)
  def factor(n: Int): Seq[(Int, Int)] = {

    def divRep(a: Int, b: Int): (Int, Int) = {
      def loop(a: Int, b: Int, count: Int = 0): (Int, Int) =
        if (a % b != 0) (a, count)
        else loop(a / b, b, count + 1)

      loop(a, b)
    }

    val facs = collection.mutable.ListBuffer[(Int, Int)]()

    def loop(a: Int): Unit = {
      val ps = primes.takeWhile(_ <= math.sqrt(a)).filter(a % _ == 0) // small factors

      if (ps.size == 0) { // a is prime
        if (a != 1) facs += ((a, 1))
        return
      } else {
        val next = ps.foldLeft(a) {
          case (a, b) =>
            val (a1, count) = divRep(a, b)
            facs += ((b, count))
            a1
        }
        if (next == 1) return
        else loop(next)
      }
    }

    loop(n)
    facs
  }
  
  def divisor(n: Int): Seq[Int] = 
    factor(n).map(x => (0 to x._2).map(math.pow(x._1, _).toInt)).
      foldLeft(Seq(1)) { case (a, b) => a.flatMap { x => b.map(_ * x) } }

  def properDivisorSum(n: Int): Int = divisor(n).sum - n
  
  lazy val abundants = Stream.from(12).filter(x => properDivisorSum(x) > x)
  
  def sumOfTwoAbundants(n: Int): Boolean = {
    if(n > 28123) true
    else{
      val as = abundants.takeWhile(_ < n - 11).toIndexedSeq
      import scala.collection.Searching._
      
      ! as.forall(x => as.search(n - x) match {
        case Found(_) => false
        case InsertionPoint(_) => true 
      })
      
    }
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ns = in.take(t).map(_.toInt)

    ns foreach {n => println(if(sumOfTwoAbundants(n)) "YES" else "NO")}
  }
}