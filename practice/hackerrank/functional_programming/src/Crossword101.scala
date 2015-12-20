import scala.collection.mutable.ListBuffer

object Crossword101 {

  val MAX = 10;

  class Grid(val m: Array[Array[Char]]) {

    // Horizontally fill the grid with given row, column, word
    // If successful, return a new Grid with the word filled 
    private def hFill(r: Int, c: Int, word: String): Option[Grid] = {
      if (m(r)(c) == '+') return None
      else if (c > 0 && m(r)(c - 1) != '+') return None
      else if (c + word.length > MAX) return None
      else if (c + word.length < MAX && m(r)(c + word.length) != '+') return None
      else {
        for (i <- 0 until word.length) {
          val ch = m(r)(c + i)
          if (ch != '-' && ch != word(i))
            return None
        }
      }
      // avoid the situation that the same word is filled at this location already
      for (i <- 0 until word.length) {
        if (m(r)(c + i) != word(i)){
          // create new grid with the given word filled
          val m1 = m map {_.clone}
          for (i <- 0 until word.length)
            m1(r)(c + i) = word(i)
          return Some(new Grid(m1))
        }
      }
      None
    }
    
    // Vertically fill the grid with given row, column, word
    // If successful, return a new Grid with the word filled 
    private def vFill(r: Int, c: Int, word: String): Option[Grid] = {
      if (m(r)(c) == '+') return None
      else if (r > 0 && m(r - 1)(c) != '+') return None
      else if (r + word.length > MAX) return None
      else if (r + word.length < MAX && m(r + word.length)(c) != '+') return None
      else {
        for (i <- 0 until word.length) {
          val ch = m(r + i)(c)
          if (ch != '-' && ch != word(i))
            return None
        }
      }
      // avoid the situation that the same word is filled at this location already
      for (i <- 0 until word.length) {
        if (m(r + i)(c) != word(i)){
          // create new grid with the given word filled
          val m1 = m map {_.clone}
          for (i <- 0 until word.length)
            m1(r + i)(c) = word(i)
          return Some(new Grid(m1))
        }
      }
      None
    }

    // Build list of new grids after successfully filling the given word to this grid
    def fill(word: String): ListBuffer[Grid] = {
      val gs = new ListBuffer[Grid]()

      for (r <- 0 until MAX)
        for (c <- 0 until MAX) {
          hFill(r, c, word) match {
            case Some(grid) => gs += grid
            case None => Unit
          }
          vFill(r, c, word) match {
            case Some(grid) => gs += grid
            case None => Unit
          }
        }

      gs
    }

    override def toString(): String = {
      val sb = new StringBuilder()

      for (cs <- m) {
        for (c <- cs)
          sb append c
        sb append '\n'
      }

      sb.toString
    }
  }

  // Solve the problem by filling words one by one, building list of candidates of final solution 
  def solveLoop(grids: ListBuffer[Grid], words: List[String]): ListBuffer[Grid] = words match {
    case List() => grids
    case w :: ws => {
      val gs = ListBuffer[Grid]()
      for(g <- grids)
        gs ++= g.fill(w) 
      solveLoop(gs, ws)
    }
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val g = new Grid(in.take(10).toArray[String] map { _.toCharArray })
    val ws = in.next.trim.split(";").toList
    val solution = solveLoop(ListBuffer[Grid](g), ws)
    
    // There could be multiple solutions but print only the first one to meet the output constraint
    println(solution(0))
  }
}