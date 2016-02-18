package weekOfCode19

// Attepmted for generalization but buggy yet.

object FixTheCycles2 {

  case class Edge(from: Int, to: Int, weight: Int)

  // vertex numbering: A: 0, B: 1, C: 2, D: 3
  class Graph(ws: Array[Int]) {
    val V = 4;
    val edges = Array(Edge(3, 0, ws(0)),
      Edge(0, 1, ws(1)),
      Edge(1, 2, ws(2)),
      Edge(2, 3, ws(3)),
      Edge(0, 2, ws(4)),
      Edge(1, 3, ws(5)))

    def adj(v: Int) = v match {
      case 0 => List(edges(1), edges(4))
      case 1 => List(edges(2), edges(5))
      case 2 => List(edges(3))
      case 3 => List(edges(0))
    }

    def negCycles(e: Edge) = {
      val paths = collection.mutable.ListBuffer[Vector[Int]]()

      def dfs(v: Int, path: Vector[Int]): Unit = {
        if (path.contains(v)) {
          paths += path.dropWhile(_ != v)
          return
        }

        for (e <- adj(v)) {
          dfs(e.to, path :+ e.from)
        }
      }

      dfs(e.from, Vector[Int]())

      val cycles = paths.filter(p => p(0) == e.from && p(1) == e.to).
        map(x => (x :+ x.head).sliding(2, 1).flatMap { case Vector(v, w) => adj(v).filter(_.to == w) }.toList.sortBy(_.from)).toList
        
      val lengths = cycles.map(_.foldLeft(0) { case (wSum, Edge(_, _, w)) => wSum + w })
      
      (cycles zip lengths).filter(_._2 < 0)
    }

    override def toString(): String = {
      val sb = new collection.mutable.StringBuilder()
      sb.append("Vtxs: ").append(V).append('\n')
      for (v <- (0 until V)) {
        sb.append(v).append(": ").append(adj(v)).append('\n')
      }
      sb.toString
    }
  }

  def solve(g: Graph): Int = {

    val edgeTo = Array.fill[Edge](g.V)(null)
    val distTo = Array.fill(g.V)(5000)

    distTo(0) = 0

    // 'relax' from Bellman-Ford algorithm
    def relax(e: Edge): Boolean = {
      val dist = distTo(e.from) + e.weight
      if (dist < distTo(e.to)) {
        distTo(e.to) = dist
        edgeTo(e.to) = e
        true
      } else
        false
    }

    for (i <- (0 until g.V))
      for (e <- g.edges)
        relax(e)

    if (g.edges.forall(e => !relax(e)))
      -1
    else {
      
      // edge-negCycles mappings
      val ecs = g.edges.map{ e => (e, g.negCycles(e)) }
      
      // number of all negCycles
      val num = ecs.flatMap(_._2).map(_._1).distinct.size
      
      // effective edge-cycles mappings
      val effEcs = ecs.filter(_._2.size == num)
      
      println(ecs.flatMap(_._2).map(_._1).distinct.toList)
      println(effEcs.toList)
      
      if(effEcs.size == 0)
        -1
      else
        - effEcs.map(_._2.map(_._2).min).min
    }
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val ws = in.next.trim.split("\\s+").map(_.toInt).toArray

    val g = new Graph(ws)
    println(solve(g))
  }
}