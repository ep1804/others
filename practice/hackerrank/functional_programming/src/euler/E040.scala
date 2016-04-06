package euler

import scala.collection.Searching._

object ChampernowneConstant {

  lazy val a: Stream[Long] = Stream.from(0).map(x => 9L * x * math.pow(10, x - 1).toLong)

  lazy val b: Stream[Long] = 0L #:: (b zip a.tail).map { case (m, n) => m + n }

  val ds = b.takeWhile(_ <= math.pow(10, 18).toLong).toArray

  def digit(n: Long): Long = {

    val e = ds.search(n) match {
      case Found(x) => x
      case InsertionPoint(x) => x
    }
    val f = n - ds(e - 1)
    val g = (f / e)
    val h = (f % e).toInt

    val start = math.pow(10, e - 1).toLong
    if (h == 0) {
      (start + g - 1).toString.last - '0'
    } else {
      (start + g).toString.apply(h - 1) - '0'
    }
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val cs = in.take(t).map(_.split("\\s+").map(_.toLong))

    // println(ds.toList)
    // println((1 to 2010).map(x => digit(x)).mkString(""))
    
    cs foreach { c =>
      println((c map digit).foldLeft(1L){case(a, b) => a * b})
    }
  }
}