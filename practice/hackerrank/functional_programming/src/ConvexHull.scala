import scala.collection.mutable.ListBuffer

object ConvexHull {

  // score of counter clock wise degree
  // if ccw >  0, counter clock wise turn
  // if ccw <  0, clock wise turn
  // if ccw == 0, collinear
  def ccw(a: Tuple2[Int,Int], b: Tuple2[Int,Int], c: Tuple2[Int,Int]): Int = { 
    val dx1 = b._1 - a._1
    val dy1 = b._2 - a._2
    val dx2 = c._1 - a._1
    val dy2 = c._2 - a._2
    dx1 * dy2 - dx2 * dy1    
  }
  
  def distance(a: Tuple2[Int,Int], b: Tuple2[Int,Int]): Double = 
    math.sqrt(math.pow(b._1 - a._1, 2) + math.pow(b._2 - a._2, 2)) 

  
  def convexHull(ps: List[Tuple2[Int, Int]], start: Tuple2[Int, Int]): ListBuffer[Tuple2[Int, Int]] = {
    val acc = ListBuffer(start)
    
    def loop(ps: List[Tuple2[Int, Int]]): Unit = ps match {
      case List() => Unit
      case x :: xs => {
        if(x == start) 
          loop(xs)
        else if(x == acc.last) loop(xs)
        else if(acc.size == 1) { acc += x; loop(xs) }
        else { 
          val p0 = acc(acc.length - 2)
          val p1 = acc(acc.length - 1)
          val cmp = ccw(p0, p1, x)
          if(cmp > 0) { acc += x; loop(xs) }
          else if(cmp < 0) { acc.trimEnd(1); loop(ps) }
          else {
            if(distance(p0, p1) < distance(p0, x)) { acc.trimEnd(1); acc += x; loop(xs) }
            else loop(xs)
          }
        }
      }
    }
    
    loop(ps)
    acc
  }
  
  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val N = in.next.trim.toInt
    val ps = in.take(N).map {x => x.trim.split("\\s+").map{_.toInt} match {case Array(x, y) => (x, y)}} .toList
    
    val minY = ps.minBy[Int](x => x._2)._2
    val start = ps.filter(x => x._2 == minY).minBy[Int](x => x._1)
    val psSorted = ps.distinct filter {_ != start} sortWith { (a, b) => { 
      val cmp = ccw(start, a, b)
      if(cmp > 0) true
      else if(cmp < 0) false
      else distance(start, a) > distance(start, b)
    }}
    
    val ch = convexHull(psSorted, start) 
    
    // for debugging display
    println("\nx,y")
    for(p <- psSorted)
      printf("%d,%d\n", p._1, p._2)
      
    println("\nx,y")
    for(p <- ch)
      printf("%d,%d\n", p._1, p._2)

    
    val ch0 = ch.toArray
    ch.trimStart(1)
    ch += start
    
    val perimeter = (ch0 zip ch) map {x => distance(x._1, x._2)} sum
    
    println(perimeter)
    
  }
}