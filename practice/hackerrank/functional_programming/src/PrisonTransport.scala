object PrisonTransport {
  
  private class Connectivity(V: Int){
    val parents = (0 until V).toArray
    
    def connect(v: Int, w: Int): Unit = {
      val r = root(v)
      parents(root(w)) = r
      parents(w) = r // optimization
    }
    
    def root(v: Int): Int = 
      if(parents(v) == v) v else root(parents(v))
    
    def grouped: Iterable[Int] = 
      parents.toList.groupBy(x => root(x)).map(x => x._2.size)      
  }
    
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    val N = in.next.toInt
    val M = in.next.toInt
    val pqs = in.take(M).map(x => x.trim.split("\\s+").map(_.toInt))
    
    val con = new Connectivity(N)
    
    for(pq <- pqs){
      con.connect(pq(0) - 1, pq(1) - 1)
    }
    
    println(con.grouped.map(x => math.ceil(math.sqrt(x)).toInt).sum)    
  }
}