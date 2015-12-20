object StringReduction {
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    val s = in.next
    
    val R = 26 // radix
    
    val marked =  Array.fill[Boolean](26)(false);
    
    val res = new StringBuilder()
    
    for(c <- s){
      
      val idx = c.toInt - 'a'
      
      if(marked(idx))
        Unit
      else{
        marked(idx) = true
        res append c
      }       
    }
    
    println(res)    
  }
}