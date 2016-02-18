package weekOfCode19

object TwoRobots2 {

  case class Mov(from: Int, to: Int)

  def solve(m: Int, n: Int, moves: Array[Mov]): Int = {

    val distBase = moves.map { case Mov(a, b) => math.abs(a - b) }.sum

    val cache = collection.mutable.Map[(Int, Int, Int), Int]()

    // additional distance when single robot works up to n'th movement
    def distSingle(n: Int): Int = {
      if (n == 1) 0
      else moves.take(n).sliding(2, 1).map { case Array(Mov(a, b), Mov(c, d)) => math.abs(b - c) }.sum
    }

    def dist(r1: Int, r2: Int, moves: Array[Mov]): Int = {
      if (moves.size == 0) return 0

      if(cache.contains((r1, r2, moves.length))) return cache((r1, r2, moves.length))
      
      val mv = moves(0)

      val res1 = math.abs(r1 - mv.from) + { if (mv.to < r2) dist(mv.to, r2, moves.tail) else dist(r2, mv.to, moves.tail) }
      val res2 = math.abs(r2 - mv.from) + { if (mv.to > r1) dist(r1, mv.to, moves.tail) else dist(mv.to, r1, moves.tail) }
      val res = math.min(res1, res2)
      
      cache.put((r1, r2, moves.length), res)
      res
    }

    // do cases where Robot 2 start working after x movements of Robot 1
    val distAdd = if (n <= 2) {
      0
    } else
      (1 until n).map { x =>
        val r1 = moves(x - 1).to
        val r2 = moves(x).to
        distSingle(x) + dist(r1, r2, moves.drop(x + 1))
      }.min

    distBase + distAdd
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    for (i <- (0 until t)) {
      val Array(m, n) = in.next.trim.split(" ").map(_.toInt)
      val moves = in.take(n).map(_.trim.split(" ").map(_.toInt)).map(x => Mov(x(0), x(1))).toArray

      println(solve(m, n, moves))
    }
  }

}