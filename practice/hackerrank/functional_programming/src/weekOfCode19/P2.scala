package weekOfCode19

object TwoRobots {

/* 

Greedy approach fails with following test case.
It will answer 303 with robot sequence r1, r2, r2, r2, r2
while correct answer is 296 with sequence r1, r2, r2, r1, r2:

1
100 5
100 1
100 1
1 20
11 12
50 12

*/
  
  def solve(m: Int, n: Int, moves: Array[Array[Int]]) = {

    // sum of movements with given time when Robot 2 starts to move
    // r2in >= 2
    def sum(r2in: Int): Int = {
      var r1 = moves(0)(1)
      var r2 = -r2in

      var sum = 0

      // computing sum of movements WITHOUT candy
      for (i <- (1 until n)) {
        if (r2 < -2) { // move Robot 1
          r2 += 1
          sum += math.abs(r1 - moves(i)(0))
          r1 = moves(i)(1)
        } else if (r2 == -2) { // move Robot 2
          r2 = moves(i)(1)
        } else {
          val dest = moves(i)(0)
          val d1 = math.abs(r1 - dest)
          val d2 = math.abs(r2 - dest)
          if (d1 < d2) { // move Robot 1
            sum += d1
            r1 = moves(i)(1)
          } else { // move Robot 2
            sum += d2
            r2 = moves(i)(1)
          }
        }
      }

      sum
    }
    
    val sum2 = moves.map { case Array(a, b) => math.abs(a - b) }.sum
    
    if(n > 2){
      sum2 + (2 to n).map(sum).min
    }else{
      sum2
    }
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    for (i <- (0 until t)) {
      val Array(m, n) = in.next.trim.split(" ").map(_.toInt)
      val moves = in.take(n).map(_.trim.split(" ").map(_.toInt)).toArray

      println(solve(m, n, moves))
    }
  }
}