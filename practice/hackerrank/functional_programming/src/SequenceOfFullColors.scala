import scala.annotation.tailrec

object SequenceOfFullColors {

  def isFull(s: String): Boolean = {

    @tailrec
    def loop(cs: List[Char], r: Int, g: Int, y: Int, b: Int): Boolean = {
      if (math.abs(r - g) > 1 || math.abs(y - b) > 1) false
      else
        cs match {
          case List() => if (r != g || y != b) false else true
          case c :: cs1 => c match {
            case 'R' => loop(cs1, r + 1, g, y, b)
            case 'G' => loop(cs1, r, g + 1, y, b)
            case 'Y' => loop(cs1, r, g, y + 1, b)
            case 'B' => loop(cs1, r, g, y, b + 1)
          }
        }
    }

    loop(s.toList, 0, 0, 0, 0)
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val T = in.next.toInt
    val bs = in.take(T).map(isFull(_))

    for (b <- bs)
      if (b) println("True")
      else println("False")
  }
}