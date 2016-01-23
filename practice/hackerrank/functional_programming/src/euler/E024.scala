package euler

object LexicographicPermutations  { 
  
  val s = "abcdefghijklm"
  
  val map = collection.mutable.Map[Long, String]() // FIXME bad code
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ns = in.take(t).map(_.toLong)

    val ss = s.permutations
    
    var i: Long = 1
    
    for(s <- ss){
      map.put(i, s)
      i = i + 1
    }
    
    ns foreach {n => println(map(n))}
  }
}