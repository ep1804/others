package andes

object MapTest {
  def main(args: Array[String]) {
    val in = io.Source.stdin.getLines
    val N = in.next.toInt

    val dic = in.take(2 * N).grouped(2).map { case Seq(a, b) => (a, b) }.toMap

    in.takeWhile(_ != null) foreach {
      x => if (dic.contains(x)) println(x + "=" + dic(x)) else println("Not found")
    }
  }

}