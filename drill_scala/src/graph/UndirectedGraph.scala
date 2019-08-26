import scala.collection.mutable.ArrayBuffer

object UndirectedGraph {

  class Graph(v: Int) extends GraphTrait {
    adjs = Array.fill(v)(ArrayBuffer[Int]())
  }

  def main(args: Array[String]): Unit = {
    val lines = io.Source.stdin.getLines

    val nv = lines.next().toInt

    val g = new Graph(nv)

    for(i <- 0 until nv){
      val line = lines.next()
      val Array(v, w) = line.trim.split("\\s+").map(_.toInt)
      g.addEdge(v, w)
    }

    println(g)

  }

}
