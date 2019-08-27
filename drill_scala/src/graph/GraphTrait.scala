package graph

import scala.collection.mutable.ArrayBuffer

trait GraphTrait {

  protected var adjs: Array[ArrayBuffer[Int]] = _
  protected var edges: Int = 0

  /**
    * @return number of vertices
    */
  def V: Int = adjs.length

  /**
    * @return number of edges
    */
  def E: Int = edges

  /**
    * adjacency list given vertex ID
    *
    * @param v vertex ID
    * @return adjacency list
    */
  def adj(v: Int): Iterable[Int] = adjs(v)

  /**
    * add new edge
    *
    * @param v from vertex
    * @param w to vertex
    */
  def addEdge(v: Int, w: Int): Unit = {
    adjs(v) += w
    adjs(w) += v
    edges += 1
  }

  override def toString: String = {
    val sb = new StringBuilder
    sb.append("vertices: " + V).append("\n")
    sb.append("edges: " + E).append("\n")
    sb.append("adjacency list:\n")
    adjs.zipWithIndex.foreach { case (l, i) => sb.append(s"$i -> ${l.mkString(", ")}\n") }
    sb.toString
  }
}
