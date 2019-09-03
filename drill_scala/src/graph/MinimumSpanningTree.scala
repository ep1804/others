package graph

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object MinimumSpanningTree {

  class Edge(v1: Int, v2: Int, val weight: Double) extends Ordered[Edge] {

    def adj: Seq[Int] = Seq(v1, v2)

    def either: Int = v1

    def other(v: Int): Int = if (v == v1) v2 else v1

    override def compare(that: Edge): Int = {
      if (this.weight < that.weight) 1
      else if (this.weight > that.weight) -1
      else 0
    }

    override def toString: String = s"$v1-$v2-$weight"
  }

  class Graph(val V: Int) {

    private val adjs = Array.fill(V)(ArrayBuffer[Edge]())

    private val edges = ArrayBuffer[Edge]()

    def addEdge(v1: Int, v2: Int, weight: Double): Unit = {
      val edge = new Edge(v1, v2, weight)
      adjs(v1) += edge
      adjs(v2) += edge
      edges += edge
    }

    def adj(v: Int): Seq[Edge] = adjs(v)

    def primMst: Seq[Edge] = {

      val mst = ArrayBuffer[Edge]()

      val added = Array.fill(V)(false)

      val pq = mutable.PriorityQueue[Edge]()

      added(0) = true
      adjs(0).foreach(e => pq.enqueue(e))

      while(pq.nonEmpty && mst.size < V){
        val e = pq.dequeue()
        if(!e.adj.forall(x => added(x))){
          mst += e
          e.adj.foreach{ v =>
            if(!added(v)){
              added(v) = true
              adjs(v).foreach(ve => pq.enqueue(ve))
            }
          }
        }
      }

      mst
    }

    override def toString: String = {
      val sb = new StringBuilder
      sb.append("vertices: ").append(V)
      for (i <- adjs.indices) {
        sb.append("\n  vtx %2d: ".format(i)).append(adjs(i).mkString(" "))
      }
      sb.toString
    }

  }

  def main(args: Array[String]): Unit = {
    val lines = io.Source.stdin.getLines
    val numV = lines.next.toInt

    val g = new Graph(numV)
    while (lines.hasNext) {
      val line = lines.next.trim
      if (line.nonEmpty) {
        val Array(a, b, c) = line.split("\\s+")
        g.addEdge(a.toInt, b.toInt, c.toDouble)
      }
    }

    println("Graph input:\n" + g)

    println("Priority queue test:\n")
    val pq = mutable.PriorityQueue[Edge]()

    pq.enqueue(new Edge(10, 10, 0.1), new Edge(3, 3, 0.3), new Edge(2, 2, 0.2))

    println(pq.dequeue())
    println(pq.dequeue())
    println(pq.dequeue())

    println("Prim MST:\n")
    g.primMst.foreach(println)
  }
}
