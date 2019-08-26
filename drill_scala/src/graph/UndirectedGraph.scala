package graph

import java.util

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object UndirectedGraph {

  class Graph(numVertex: Int) extends GraphTrait {
    adjs = Array.fill(numVertex)(ArrayBuffer[Int]())

    private val notVisited = Array.fill(numVertex)(true)
    private val parent = Array.fill(numVertex)(-1)

    def dfs(s: Int): Unit = {

      util.Arrays.fill(notVisited, true)
      util.Arrays.fill(parent, -1)

      val visitOrder = ArrayBuffer[Int]()

      def go(v: Int): Unit = {
        notVisited(v) = false
        visitOrder += v
        for (w <- adjs(v)) {
          if (notVisited(w)) {
            parent(w) = v
            go(w)
          }
        }
      }

      go(s)

      println(s"DFS from $s: ${visitOrder.mkString(" ")}")
    }

    def bfs(s: Int): Unit = {

      util.Arrays.fill(notVisited, true)
      util.Arrays.fill(parent, -1)

      val visitOrder = ArrayBuffer[Int]()
      val toVisit = mutable.Queue[Int]()

      toVisit.enqueue(s)
      notVisited(s) = false

      while (toVisit.nonEmpty) {
        val v = toVisit.dequeue()
        visitOrder += v

        for (w <- adjs(v)) {
          if (notVisited(w)) {
            parent(w) = v
            toVisit.enqueue(w)
            notVisited(w) = false
          }
        }
      }

      println(s"BFS from $s: ${visitOrder.mkString(" ")}")
    }

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

    g.dfs(0)

    g.bfs(0)
  }

}
