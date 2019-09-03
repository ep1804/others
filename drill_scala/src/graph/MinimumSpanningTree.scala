package graph

import scala.collection.mutable.ArrayBuffer

object MinimumSpanningTree {

  class Graph(val V: Int) {

    class Edge(v1: Int, v2: Int, val weight: Double) {
      override def toString: String = s"$v1-$v2-$weight"
    }

    private val adjs = Array.fill(V)(ArrayBuffer[Edge]())

    private val edges = ArrayBuffer[Edge]()

    def addEdge(v1: Int, v2: Int, weight: Double): Unit = {
      val edge = new Edge(v1, v2, weight)
      adjs(v1) += edge
      adjs(v2) += edge
      edges += edge
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

  }
}
