package weekOfCode19

object FixTheCycles {

  def solve(ws: Array[Int]): Int = ws match {
    case Array(a, b, c, d, e, f) => {
      List(a + b + c + d, a + b + f, a + e + d).filter(_ < 0) match {
        case Nil => 0
        case ns => - ns.min
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val ws = in.next.trim.split(" ").map(_.toInt)

    println(solve(ws))
  }

}