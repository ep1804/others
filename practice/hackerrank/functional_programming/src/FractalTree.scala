object FractalTree {

  def isOne(x: Int, y: Int, rootX: Int, rootY: Int, height: Int, count: Int): Boolean = {
    if(y >= rootY + height)
      if(count == 1) false
      else{
        if(x < rootX) isOne(x, y, rootX - height / 2, rootY + height, height / 2, count - 1)
        else isOne(x, y, rootX + height / 2, rootY + height, height / 2, count - 1)
      }
    else if(y < rootY + height / 2)
      if( x == rootX ) true
      else false
    else // y <= rootY + height
      if(y - (rootY + height / 2 - 1) == - x + rootX || y - (rootY + height / 2 - 1) == x - rootX ) true
      else false
  }
  
  def main(args: Array[String]): Unit = {
    val N = io.Source.stdin.getLines.next.toInt

    for (y <- 63 to (1, -1)){
      for (x <- 1 to 100)
        if (isOne(x, y, 50, 1, 32, N)) print("1")
        else print("_")
      println()
    }

  }

}