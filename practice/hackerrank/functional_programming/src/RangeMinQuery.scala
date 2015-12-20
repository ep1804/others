object RangeMinQuery {
  
  
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val M = in.next.trim.split(" ")(1).toInt
    val numbers =  in.next.trim.split(" ").map(_.toInt).toList
    val ranges = in.take(M).toList.map(x => x.trim.split(" ").map(_.toInt).toList)
    
    println(numbers)
    println(ranges)
  }
}