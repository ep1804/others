package graph

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object UndirectedGraph {

  class Graph(numVertex: Int) extends GraphTrait {
    adjs = Array.fill(numVertex)(ArrayBuffer[Int]())

    override def addEdge(v: Int, w: Int): Unit = {
      adjs(v) += w
      adjs(w) += v
      edges += 1
    }
  }

  def dfsReach(g: Graph, from: Int): Seq[Int] = {
    val reach = ArrayBuffer[Int]()

    val visited = Array.fill(g.V)(false)

    def dfs(v: Int): Unit = {
      visited(v) = true
      reach += v
      for (w <- g.adj(v)) {
        if (!visited(w)) {
          dfs(w)
        }
      }
    }

    dfs(from)

    reach
  }

  def bfsReach(g: Graph, from: Int): Seq[Int] = {

    val reach = ArrayBuffer[Int]()

    val queue = mutable.Queue[Int]()
    val queued = Array.fill(g.V)(false)

    queue.enqueue(from)
    queued(from) = true

    while (queue.nonEmpty) {
      val v = queue.dequeue()
      reach += v

      for (w <- g.adj(v)) {
        if (!queued(w)) {
          queue.enqueue(w)
          queued(w) = true
        }
      }
    }

    reach
  }

  def connectedComponents(g: Graph): Array[Int] = {
    val grouped = Array.fill(g.V)(0)
    var gId = 1

    def dfs(v: Int): Unit = {
      grouped(v) = gId
      for (w <- g.adj(v)) {
        if (grouped(w) == 0) dfs(w)
      }
    }

    for (v <- 0 until g.V) {
      if (grouped(v) == 0) {
        dfs(v)
        gId += 1
      }
    }

    grouped
  }

  def main(args: Array[String]): Unit = {
    val lines = io.Source.stdin.getLines

    val nv = lines.next().toInt

    val g = new Graph(nv)

    while (lines.hasNext) {
      val line = lines.next.trim
      if (line.nonEmpty) {
        val Array(v, w) = line.trim.split("\\s+").map(_.toInt)
        g.addEdge(v, w)
      }
    }

    println(g)

    val dfsSeq = dfsReach(g, 0)
    println(s"DFS from 0: ${dfsSeq.mkString(" ")}")

    val bfsSeq = bfsReach(g, 0)
    println(s"BFS from 0: ${bfsSeq.mkString(" ")}")

    val cc = connectedComponents(g)
    println(s"CC: ${cc.mkString(" ")}")
  }

}
