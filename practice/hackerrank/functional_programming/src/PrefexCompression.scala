object PrefexCompression {
  
  def commonPrefix(s1: String, s2: String): Int = {
    val minLen = math.min(s1.length, s2.length)
    for(i <- 0 until minLen)
      if(s1(i) != s2(i)) return i;
    return minLen
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val strs = in.take(2).toList
    val pLen = commonPrefix(strs(0), strs(1))
    
    println(pLen + " " + strs(0).substring(0, pLen))
    println(strs(0).length - pLen + " " + strs(0).substring(pLen))
    println(strs(1).length - pLen + " " + strs(1).substring(pLen))
  }
}