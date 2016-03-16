package euler

import scala.collection.Searching._
import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable.TreeMap

// Employing: Tree of Pythogorean triples, Complex data structures, Little bit simplified

object IntegerRightTriangles3 {

  // https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples
  // limit: max perimeter
  def buildPythagoreanPerimeters(limit: Int) = {

    val peri = collection.mutable.Map[Int, Int]()

    def loop(x: (Int, Int)): Unit = x match {
      case (u, v) => {
        if (u * v + u * u > limit) return

        val p = u * v + (u * u - v * v) / 2 + (u * u + v * v) / 2

        (p to limit by p) foreach { p =>
          if (peri.contains(p)) peri.update(p, peri(p) + 1)
          else peri += (p -> 1)
        }

        loop(2 * u - v, u)
        loop(2 * u + v, u)
        loop(u + 2 * v, v)
      }
    }

    loop(3, 1)
    peri
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.trim.toInt
    val ns = in.take(t).map(_.trim.toInt).toList

    val limit = ns.max

    // perimeter --> number of triples
    val map1 = buildPythagoreanPerimeters(limit)

    
    println("map1 done")
    // sorted
    val map2 = map1.toSeq.sortBy(_._1)

    println("map2 done")
    
    val maxPeri = ArrayBuffer[Int]()

    var max = 0
    for ((k, v) <- map2) {
      if (v > max) {
        maxPeri += k
        max = v
      }
    }

    ns foreach { n =>
      maxPeri.search(n) match {
        case InsertionPoint(x) => println(maxPeri(x - 1))
        case Found(x) => println(maxPeri(x))
      }
    }
  }
}