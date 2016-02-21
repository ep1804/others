package weekOfCode19

import scala.collection.mutable.ListBuffer

// Seems to be able to prove that:
// Max min-inter-distance of permutation of length 2n = n
// Max min-inter-distance of permutation of length 2n + 1 = n

// Also that:
// For cases with max min-inter-distance, max-inter-distance is n + 1

object FindThePermutation {

  lazy val pow2: Stream[Long] = 1L #:: pow2.map(_ * 2)

  lazy val pow2sum: Stream[Long] = 1L #:: (pow2sum zip pow2.tail).map { case (a, b) => a + b }

  val p2s = pow2sum.takeWhile(_ <= 1000000000000000000L).toIndexedSeq

  lazy val facs: Stream[Long] = 1L #:: (facs zip Stream.from(2)).map{ case (a, b) => a * b }
  
  val factorial = facs.takeWhile(_ <= 1000000000000000000L).toIndexedSeq
  
//  def factorial(n: Long): Long = {
//    def loop(n: Long, acc: Long): Long =
//      if (n == 1) acc
//      else loop(n - 1, acc * n)
//
//    loop(n, 1)
//  }

  def combi(a: Long, b: Long): Long = {
    if (a == b) return 1L
    val b1 = math.min(b, a - b)
    (a to (a - b1 + 1) by -1).toList.foldLeft(1L)(_ * _) / (b1 to 1 by -1).toList.foldLeft(1L)(_ * _)
  }

  // idx0 starts from 1
  def combination[T](a: Seq[T], n: Long, idx1: Long): Seq[T] = {
    if (n == 0) return Nil

    val idx = idx1 - 1
    val res = ListBuffer[T]()
    val b = ListBuffer.concat(a)

    def loop(idx: Long, n1: Long): Unit = {
      if (n1 == 0) return

      if (n1 == 1) {
        res += b(idx.toInt)
        return
      }

      val lim = combi(b.size - 1, n1 - 1)

      if (idx < lim) {
        res += b.remove(0)
        loop(idx, n1 - 1)
      } else {
        b.remove(0)
        loop(idx - lim, n1)
      }
    }

    loop(idx, n)
    res
  }

  // idx1 starts from 1
  def permutation[T](a: Seq[T], idx1: Long): Seq[T] = {
    val idx = idx1 - 1
    val res = ListBuffer[T]()
    val b = ListBuffer.concat(a)

    def loop(idx: Long): Unit = {

      if (idx < 1) return

      val fac = factorial(b.length - 1)
      val first = idx / fac
      val nextIdx = idx % fac

      res += b.remove(first.toInt)

      loop(nextIdx)
    }

    loop(idx)
    res ++ b
  }

  // k1 starts from 1
  def genOdd(m: Int, k1: Long): List[Int] = {

    val n = m / 2
    val a = ListBuffer[List[Int]]()
    val x = List(1, n + 1, m)
    val xr = List(m, n + 1, 1)

    def loop1(c: Int): Unit =
      if (c > n) Unit
      else {
        a.prepend(List(c, n + c))
        loop1(c + 1)
      }

    def loop2(c: Int): Unit =
      if (c == 1) Unit
      else {
        a.prepend(List(n + c, c))
        loop2(c - 1)
      }

    val stop1 = 1 + p2s(n - 2)
    val stop2 = stop1 + n - 1
    val stop3 = stop2 + n - 1
    val stop4 = stop3 + 1 + p2s(n - 2)

    if (k1 == 1) {
      loop1(2)
      x ++: a.toList.flatten

    } else if (k1 <= stop1) {

      loop1(2)
      var k = k1 - 1

      val headPassed = p2s.takeWhile(_ < k).size
      if (headPassed > 0) k -= p2s(headPassed - 1)

      lazy val cs = 1L #:: Stream.from(1).map(x => combi(n - 2, x))
      lazy val cs2sum: Stream[Long] = 1L #:: (cs2sum zip cs.tail).map { case (a, b) => a + b }

      val combiPassed = cs2sum.takeWhile(_ < k).size
      if (combiPassed > 0) k -= cs2sum(combiPassed - 1)

      val head = a.remove(a.size - headPassed - 1)
      val headRest = combination(a.takeRight(headPassed), combiPassed, k)
      val tail = headRest.map(x => a -= x)

      head ++: headRest.flatten ++: x ++: a.toList.flatten

    } else if (k1 <= stop2) {

      loop1(2)
      val k = k1 - stop1
      val a1 = a.toList.flatten
      val spl = (m - 2 - 2 * k).toInt

      a1.take(spl) ++: xr ++: a1.drop(spl)

    } else if (k1 <= stop3) {

      loop2(n)
      val k = k1 - stop2
      val a1 = a.toList.flatten
      val spl = (2 * k - 1).toInt

      a1.take(spl) ++: x ++: a1.drop(spl)

    } else if (k1 <= stop4) {

      loop2(n)
      var k = k1 - stop3

      if (k == 1) return a.toList.flatten ++: xr
      
      k -= 1

      lazy val com = Stream.from(1).map(x => combi(n - 1, x))
      lazy val comSum: Stream[Long] = com.head #:: (com.tail zip comSum).map { case (a, b) => a + b }

      val comPassed = comSum.takeWhile(_ < k).size
      if (comPassed > 0) k -= comSum(comPassed - 1)

      //println(a.toList)
      //println(s"k1: ${k1 - stop3}, k: $k, comPassed: $comPassed")
      
      val left = combination(a, a.length - comPassed - 1, k)
      //println("left: " + left)

      left.toList.flatten ++: xr ++: a.filter(!left.contains(_)).toList.flatten

    } else {

      List(-1)

    }
  }

  // k starts from 1
  def genEven(m: Int, k: Long): List[Int] = {

    val n = m / 2

    def loop1(c: Int, acc: List[Int]): List[Int] =
      if (c > n) return acc
      else loop1(c + 1, c :: (n + c) :: acc)

    def loop2(c: Int, acc: List[Int]): List[Int] =
      if (c == 0) return acc
      else loop2(c - 1, (n + c) :: c :: acc)

    if (k == 1) loop1(1, Nil)
    else if (k == 2) loop2(n, Nil)
    else List(-1)
  }

  def solve(m: Int, k: Long): Seq[Int] = {
    if (m == 1) {
      if (k == 1) List(1) else List(-1)
    } else if (m == 3) {
      k match {
        case 1 => List(1, 2, 3)
        case 2 => List(1, 3, 2)
        case 3 => List(2, 1, 3)
        case 4 => List(2, 3, 1)
        case 5 => List(3, 1, 2)
        case 6 => List(3, 2, 1)
      }
    } else if (m % 2 == 0) {
      genEven(m, k)
    } else {
      genOdd(m, k)
    }
  }

  def main(args: Array[String]): Unit = {
    //    val in = io.Source.stdin.getLines
    //    val t = in.next.trim.toInt
    //    val ns = in.take(t).map(_.trim.split(" ").map(_.toLong))
    //
    //    ns foreach { case Array(m, k) => println(solve(m.toInt, k).mkString(" ")) }

    for (i <- (1 to 30))
      println(genOdd(9, i))

  }
}