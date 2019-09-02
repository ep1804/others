package graph

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object DirectedGraph {

  class Digraph(numVertices: Int) extends GraphTrait {

    adjs = Array.fill(numVertices)(ArrayBuffer[Int]())

    override def addEdge(v: Int, w: Int): Unit = {
      adjs(v) += w
    }

    def reverseGraph: Digraph = {
      val res = new Digraph(V)

      for (v <- 0 until V) {
        for (w <- adj(v)) {
          res.addEdge(w, v)
        }
      }

      res
    }
  }

  def dfsReach(g: Digraph, from: Int): Seq[Int] = {
    val reach = ArrayBuffer[Int]()
    val visited = Array.fill(g.V)(false)

    def dfs(v: Int): Unit = {
      visited(v) = true
      reach += v
      for (w <- g.adj(v)) {
        if (!visited(w)) dfs(w)
      }
    }

    dfs(from)

    reach
  }

  def bfsReach(g: Digraph, from: Int): Seq[Int] = {
    val reach = ArrayBuffer[Int]()

    val queue = mutable.Queue[Int]()
    val queued = Array.fill(g.V)(false)

    queue.enqueue(from)
    queued(from) = true

    while (queue.nonEmpty) {
      val v = queue.dequeue()
      reach += v
      for (w <- g.adj(v)) {
        if (queued(w)) {
          queue.enqueue(w)
          queued(w) = true
        }
      }
    }

    reach
  }

  def dfsPortOrder(g: Digraph): Seq[Int] = {

    val ord = ArrayBuffer[Int]()

    val notVisited = Array.fill(g.V)(true)

    def dfs(v: Int): Unit = {
      notVisited(v) = false
      for (w <- g.adj(v)) {
        if (notVisited(w)) {
          dfs(w)
        }
      }
      ord += v
    }

    for (v <- 0 until g.V) {
      if (notVisited(v)) dfs(v)
    }

    ord
  }

  def stronglyConnectedComponents(g: Digraph): Seq[Int] = {
    val rg = g.reverseGraph

    val grouped = Array.fill(g.V)(0)
    var gId = 1

    def dfs(v: Int): Unit = {
      grouped(v) = gId
      for (w <- g.adj(v)) {
        if (grouped(w) == 0) {
          dfs(w)
        }
      }
    }

    for (v <- dfsPortOrder(rg).reverse) {
      if (grouped(v) == 0) {
        dfs(v)
        gId += 1
      }
    }

    grouped
  }

  def main(args: Array[String]): Unit = {
    val lines = io.Source.stdin.getLines

    val nv = lines.next.toInt

    val g = new Digraph(nv)

    while (lines.hasNext) {
      val line = lines.next.trim
      if (line.nonEmpty) {
        val Array(v, w) = line.split("\\s+").map(_.toInt)
        g.addEdge(v, w)
      }
    }

    println("Graph:\n" + g)

    val dfsSeq = dfsReach(g, 0)
    println(s"DFS from 0: ${dfsSeq.mkString(" ")}")

    val bfsSeq = bfsReach(g, 0)
    println(s"BFS from 0: ${bfsSeq.mkString(" ")}")

    val dfsPos = dfsPortOrder(g)
    println(s"DFS post order: ${dfsPos.mkString(" ")}")

    val rg = g.reverseGraph
    println("Reversed graph:\n" + rg)

    val scc = stronglyConnectedComponents(g)
    println("SCC: " + scc.mkString(" "))
  }

}
