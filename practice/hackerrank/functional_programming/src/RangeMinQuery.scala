object RangeMinQuery {

  abstract class Node

  object Empty extends Node {
    override def toString = "."
  }

  case class Stem(val idMin: Int, val idMax: Int, valueMin: Int, left: Node, right: Node) extends Node {
    override def toString =
      "(" + left + " " + idMin + "-" + idMax + "-" + valueMin + " " + right + ")"
  }

  case class Leaf(id: Int, value: Int) extends Node {
    override def toString =
      "[" + id + "-" + value + "]"
  }

  def buildTree(ns: List[Tuple2[Int, Int]]): Node = ns match {
    case List() => Empty
    case List((v, id)) => Leaf(id, v)
    case _ => {
      val h = ns.length / 2
      val l = buildTree(ns.take(h))
      val r = buildTree(ns.drop(h))
      (l, r) match {
        case (Leaf(id1, v1), Leaf(id2, v2)) =>
          Stem(math.min(id1, id2), math.max(id1, id2), math.min(v1, v2), l, r)
        case (Leaf(id, v), Stem(iMin, iMax, vMin, _, _)) =>
          Stem(math.min(id, iMin), math.max(id, iMax), math.min(v, vMin), l, r)
        case (Stem(iMin, iMax, vMin, _, _), Leaf(id, v)) =>
          Stem(math.min(id, iMin), math.max(id, iMax), math.min(v, vMin), l, r)
        case (Stem(iMin1, iMax1, vMin1, _, _), Stem(iMin2, iMax2, vMin2, _, _)) =>
          Stem(math.min(iMin1, iMin2), math.max(iMax1, iMax2), math.min(vMin1, vMin2), l, r)
      }
    }
  }

  def rangeMin(tree: Node, idMin: Int, idMax: Int): Int = {

    def loop(node: Node, idMin: Int, idMax: Int, vMin: Int): Int = node match {
      case Empty => vMin
      case Leaf(id, v) => if(id >= idMin && id <= idMax) math.min(v, vMin) else vMin
      case Stem(iMin, iMax, vMin, l, r) => {
        (l, r) match {
          case (Leaf(id1, v1), Leaf(id2, v2)) =>
            if(id1 >= idMin && id1 <= idMax) 
          case (Leaf(id, v), Stem(iMin, iMax, vMin, _, _)) =>
            
          case (Stem(iMin, iMax, vMin, _, _), Leaf(id, v)) =>
            
          case (Stem(iMin1, iMax1, vMin1, _, _), Stem(iMin2, iMax2, vMin2, _, _)) =>
            
        }
      }
    }

    loop(tree, idMin, idMax, Int.MaxValue)
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val M = in.next.trim.split(" ")(1).toInt
    val numbers = in.next.trim.split(" ").map(_.toInt).toList.zipWithIndex
    val ranges = in.take(M).toList.map(x => x.trim.split(" ").map(_.toInt).toList)

    println(numbers)
    println(ranges)

    val tree = buildTree(numbers)
    println(tree)
    val mins = ranges.map { case List(i1, i2) => rangeMin(tree, i1, i2) }
    println(mins.mkString("\n"))
  }
}