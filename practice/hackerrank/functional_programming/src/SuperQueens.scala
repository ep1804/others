import scala.annotation.tailrec

object SuperQueens {

  case class Board(val N: Int, val queens: List[Tuple2[Int, Int]], val lineFilled: Int) {
    
    def isSafe(p: Tuple2[Int, Int]): Boolean = 
      queens forall {q => {
        
        val x0 = q._1
        val y0 = q._2
        val x = p._1
        val y = p._2
        
        if(x0 == x) false
        else if(y0 == y) false
        else if( (x - x0) == (y - y0) || (x - x0) == -1 * (y - y0) ) false
        else{
          if( x == x0 - 2 && (y == y0 - 1 || y == y0 + 1)) false
          else if( x == x0 - 1 && (y == y0 - 2 || y == y0 + 2)) false
          else if( x == x0 + 1 && (y == y0 - 2 || y == y0 + 2)) false
          else if( x == x0 + 2 && (y == y0 - 1 || y == y0 + 1)) false
          else true
        }
      }}
    
    def fillNextLine(): List[Board] = 
      (0 until N).toList map {x => (lineFilled, x)} filter {isSafe(_)} map {x => Board(N, queens :+ x, lineFilled + 1 )}
      
    override def toString(): String = {
      val res = new StringBuilder()
      for (row <- 0 until N){
        for(col <- 0 until N)
          res append '-'
        res append '\n'
      }
      for( q <- queens){
        val idx = (N + 1) * q._1 + q._2
        res.replace(idx, idx+1, "Q")
      }
      res.toString
    }
  }

  def solveNSuperQueens(N: Int): Int = {

    @tailrec
    def loop(bs: List[Board]): List[Board] = {
      val bs2 = bs flatMap {_.fillNextLine()}
      
      //println(bs2.mkString("\n\n"))
      
      if( bs2.size == 0 || bs2(0).lineFilled == N)
        bs2
      else
        loop(bs2)
    }
    
    val solution = loop(List(Board(N, List(), 0)))
    
    //println(solution.mkString("\n"))
    
    solution.size    
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val N = in.next.toInt

    println(solveNSuperQueens(N))
  }

}