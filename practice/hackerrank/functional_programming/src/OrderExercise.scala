import scala.collection.mutable.ListBuffer
import scala.annotation.tailrec

object OrderExercise {

  def order(K: Int, a: Array[Int]): ListBuffer[(Int, Int, Int)] = {

    val ars: ListBuffer[(Int, Int, Int)] = ListBuffer[(Int, Int, Int)]() // (sum, start, end)

    @tailrec
    def scan(sum: Int, idx: Int, max: Option[Tuple3[Int, Int, Int]]): Unit = {
      
      if (idx >= a.size) return
      
      val nextSum = sum + a(idx)
      max match {
        case Some((maxSum, maxStart, maxEnd)) => {          
          if (nextSum >= 0) {
            if (idx == a.size - 1) {
              if (nextSum > maxSum){
                ars += Tuple3(nextSum, maxStart, idx)
                scan(0, idx + 1, None)
              }
              else{
                ars += max.get
                scan(0, maxEnd + 1, None)
              }
            } else {
              if (nextSum > maxSum) scan(nextSum, idx + 1, Some(Tuple3(nextSum, maxStart, idx)))
              else scan(nextSum, idx + 1, max)
            }
          } else {
            ars += max.get
            scan(0, maxEnd + 1, None)
          }
        }
        case None => {
          if (nextSum > 0) {
            if (idx == a.size - 1) {
              ars += Tuple3(nextSum, idx, idx)
              scan(0, idx + 1, None)
            } else
              scan(nextSum, idx + 1, Some(Tuple3(nextSum, idx, idx)))
          } else {
            scan(0, idx + 1, None)
          }
        }
      }
    }

    scan(0, 0, None)

    // arrays with positive sum, sorted (larger one first)
    val arsSorted = ars.sortWith((p, q) =>
      (p._1 > q._1 || (p._1 == q._1 && p._2 < q._2) || (p._1 == q._1 && p._2 == q._2 && p._3 < q._3)))

    //println(arsSorted)

    arsSorted.take(K)
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val NK = in.next.trim.split("\\s+").map(_.toInt)
    val arr = in.next.trim.split("\\s+").map(_.toInt).toArray

    println(order(NK(1), arr).map(_._1).mkString("\n"))
  }
}