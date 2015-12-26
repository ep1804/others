
object StockPrediction {

  def boxLen(ns: Array[Int], d: Int, m: Int): Int = {
    
    val lowB = ns(d)
    val uppB = ns(d) + m
    
    def leftBound(l: Int): Int = {
      if(l >= 0 && ns(l) <= uppB && ns(l) >= lowB) leftBound(l - 1)
      else l + 1
    }
    
    def rightBound(r: Int): Int = {
      if(r < ns.length && ns(r) <= uppB && ns(r) >= lowB) rightBound(r + 1)
      else r - 1
    }
    
    val l = leftBound(d - 1);
    val r = rightBound(d + 1);
    
    r - l + 1
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val N = in.next.toInt
    val ns = in.next.trim.split("\\s+").map(_.toInt).toArray
    val Q = in.next.toInt
    val qs = in.take(Q).map(x => x.trim.split("\\s+").map(_.toInt).toList).toList

    val ls = for{
      List(d, m) <- qs      
    } yield boxLen(ns, d, m)
    
    println(ls.mkString("\n"))
  }
}