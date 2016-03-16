package euler

// Employing: Tree of Pythagorean triples, Only array

object IntegerRightTriangles4 {

  // https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples
  // limit: max perimeter
  def numberOfPythagoreanTriples(limit: Int) = {

    val peri = Array.ofDim[Int](limit + 1)

    def loop(x: (Int, Int)): Unit = x match {
      case (u, v) => {
        if (u * v + u * u > limit) return

        val p = u * v + (u * u - v * v) / 2 + (u * u + v * v) / 2

        (p to limit by p) foreach { p => peri(p) += 1 }

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

    // array (perimeter) --> number of triples
    val tps = numberOfPythagoreanTriples(limit)

    var maxTps = 0
    var maxPeri = 1

    // array (perimeter p) --> perimeter with max number of triples below p 
    for (p <- 1 to limit) {
      if (tps(p) > maxTps) {
        maxTps = tps(p)
        maxPeri = p
        tps(p) = p
      } else
        tps(p) = maxPeri
    }

    ns foreach { n => println(tps(n)) }
  }
}