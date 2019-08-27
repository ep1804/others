package graph

import java.util

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object UndirectedGraph {

  class Graph(numVertex: Int) extends GraphTrait {
    adjs = Array.fill(numVertex)(ArrayBuffer[Int]())
  }

  def dfs(g: Graph, from: Int): Seq[Int] = {

    val notVisited = Array.fill(g.V)(true)

    util.Arrays.fill(notVisited, true)

    val visitOrder = ArrayBuffer[Int]()

    def go(v: Int): Unit = {
      notVisited(v) = false
      visitOrder += v
      for (w <- g.adj(v)) {
        if (notVisited(w)) {
          go(w)
        }
      }
    }

    go(from)

    visitOrder
  }

  def bfs(g: Graph, from: Int): Seq[Int] = {

    val notVisited = Array.fill(g.V)(true)

    val visitOrder = ArrayBuffer[Int]()
    val nexts = mutable.Queue[Int]()

    nexts.enqueue(from)
    notVisited(from) = false

    while (nexts.nonEmpty) {
      val v = nexts.dequeue()
      visitOrder += v

      for (w <- g.adj(v)) {
        if (notVisited(w)) {
          nexts.enqueue(w)
          notVisited(w) = false
        }
      }
    }

    visitOrder
  }

  def connectedComponents(g: Graph): Array[Int] = {
    val groupIds = Array.fill(g.V)(0)
    var gId = 1

    def markGroupId(v: Int): Unit = {
      groupIds(v) = gId
      for (w <- g.adj(v)) {
        if (groupIds(w) == 0) markGroupId(w)
      }
    }

    for (v <- 0 until g.V) {
      if (groupIds(v) == 0) {
        markGroupId(v)
        gId += 1
      }
    }

    groupIds
  }

  def main(args: Array[String]): Unit = {
    val lines = io.Source.stdin.getLines

    val nv = lines.next().toInt

    val g = new Graph(nv)

    for (_ <- 0 until nv) {
      val line = lines.next()
      val Array(v, w) = line.trim.split("\\s+").map(_.toInt)
      g.addEdge(v, w)
    }

    println(g)

    val ord1 = dfs(g, 0)
    println(s"DFS from 0: ${ord1.mkString(" ")}")

    val ord2 = bfs(g, 0)
    println(s"BFS from 0: ${ord2.mkString(" ")}")

    val conc = connectedComponents(g)
    println(s"CC: ${conc.mkString(" ")}")

  }

}
