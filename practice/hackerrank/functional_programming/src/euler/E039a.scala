package euler

import scala.collection.Searching._

// Employing: Tree of Pythogorean triples, Complex data structures

object IntegerRightTriangles2 {

  //https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples
  def treeGen(x: (Int, Int)): List[(Int, Int)] = x match {
    case (u, v) => List((2 * u - v, u), (2 * u + v, u), (u + 2 * v, v))
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.trim.toInt
    val ns = in.take(t).map(_.trim.toInt).toList

    val mx = ns.max

    lazy val tree: Stream[List[(Int, Int)]] =
      List((3, 1)) #:: tree.map(_.flatMap(treeGen).filter {
        case (u, v) => u * v + u * u <= mx
      })

    val uniqueTriples =
      tree.takeWhile(!_.isEmpty).toList.flatten.map {
        case (u, v) => (u * v, (u * u - v * v) / 2, (u * u + v * v) / 2)
      }

    val triples = uniqueTriples.
      flatMap { case t @ (a, b, c) => (1 to mx / (a + b + c)).map(i => (a * i, b * i, c * i)) }

    val pMap = triples.groupBy { case (a, b, c) => a + b + c }.map { case (k, v) => (k, v.size) }
    val pMap2 = collection.immutable.TreeMap(pMap.toArray: _*)

    val maxIdxs = collection.mutable.ArrayBuffer[Int]()

    var max = 0
    for ((k, v) <- pMap2.toIterator) {
      if (v > max) {
        maxIdxs += k
        max = v
      }
    }
    
    ns foreach { n =>
      maxIdxs.search(n) match {
        case InsertionPoint(x) => println( maxIdxs(x - 1))
        case Found(x) =>  println(maxIdxs(x))
      }
    }
  }
}