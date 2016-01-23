package euler

object E022 {
  
  def score(s: String): Int = s.map(_ - 'A' + 1).sum 
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val names = in.take(t).toArray.sorted
    val q = in.next.toInt
    val qs = in.take(q)
    
    val map = collection.mutable.Map[String, Int]()
    
    (names zip (1 to t)) foreach { case (name, idx) =>
      map.put(name, score(name) * idx)
    }
    
    qs foreach {q => println(map(q))}
  }  
}