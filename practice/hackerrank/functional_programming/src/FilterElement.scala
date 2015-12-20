import scala.collection.mutable.LinkedHashMap

object FilterElement {

  def frequentNums(nums: Array[Int], k: Int): List[Int] = {

    val count = LinkedHashMap[Int, Int]()

    def countLoop(ns: List[Int]): Unit = ns match {
      case List() => Unit
      case n :: ns1 => {
        if (count.contains(n))
          count.update(n, count(n) + 1)
        else
          count += ((n, 1))
        countLoop(ns1)
      }
    }

    countLoop(nums.toList)
    
    val res = (count filter { case (_, v) => v >= k }).keys.toList
    
    if (res.size == 0)
      List(-1)
    else
      res
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines

    val T = in.next.toInt
    val inputSet = in.take(2 * T).grouped(2).toList
    val solutionSet = inputSet map (x => {
      val K = x(0).trim.split("\\s+")(1).toInt
      val nums = x(1).trim.split("\\s+").map(_.toInt)
      frequentNums(nums, K)
    })

    println((solutionSet map { x => x.mkString(" ") }).mkString("\n"))
  }
}