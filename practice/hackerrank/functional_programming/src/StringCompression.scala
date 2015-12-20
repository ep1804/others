object StringCompression {
  
  def encode(s: List[Char]): String = {
    val sb = new StringBuilder()    
    
    def loop(cur: Char, count: Int, cs: List[Char]): Unit = cs match {
      case List() => Unit
      case List(x) => {
        if(cur == x)
          sb append (count + 1)
        else
          if(count > 1)
            sb append count append x
          else
            sb append x
      }
      case x :: xs => {
        if(cur == x)
          loop(x, count + 1, xs)
        else
          if(count > 1){
            sb append count append x
            loop(x, 1, xs)
          }else{
            sb append x
            loop(x, 1, xs)
          }
      }
    }
    
    sb append s.head    
    loop(s.head, 1, s.drop(1))
    
    sb.toString
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    
    println(encode(in.next.toList))
    
  }
}