package euler

object LargestProductInSeries {
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines

    val T = in.next.toInt

    val qs = in.take(2 * T).grouped(2).map {
      case Seq(a, b) => {
        val dk = a.split("\\s+").map(_.toInt)
        (dk(0), dk(1), b)
      }
    }

    qs foreach {
      case (d, k, str) => {
        val ns = 0 to (d - k) map (x => str.drop(x).take(k)) map (_.map(_ - '0'))
        val prods = ns.map(_.foldLeft(1)(_ * _))        
        println(prods.max)
      }
    }

  }
}