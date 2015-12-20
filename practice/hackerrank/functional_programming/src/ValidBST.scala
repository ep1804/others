object ValidBST {

  private abstract class Node {
    def incl(value: Int): Node
  }

  private object Empty extends Node {
    def incl(value: Int): Node = new NonEmpty(value, Empty, Empty)
    override def toString: String = "."
  }

  private case class NonEmpty(val value: Int, val left: Node, val right: Node) extends Node {
    def incl(value: Int): Node = {
      if (value < this.value) new NonEmpty(this.value, left.incl(value), right)
      else if (value > this.value) new NonEmpty(this.value, left, right.incl(value))
      else this
    }
    override def toString: String = "(" + left + value + right + ")"
  }

  // 1) build a BST with given order (A)
  // 2) write preorder(B) of newly built BST
  // 3) if A == B, given order is valid preorder
  def isValid(givenPreorder: List[Int]): Boolean = {

    def buildTree(tree: Node, values: List[Int]): Node = values match {
      case List() => tree
      case x :: xs => buildTree(tree.incl(x), xs)
    }
    
    def buildPreorder(x: Node): List[Int] = x match {
      case Empty => List()
      case NonEmpty(v, l, r) => v :: buildPreorder(l) ++ buildPreorder(r)   
    }

    val tree = buildTree(Empty, givenPreorder)
    
    givenPreorder.equals(buildPreorder(tree))
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines

    val T = in.next.toInt
    val inputSet = in.take(T * 2).grouped(2).map(x => x(1).split("\\s+").map(_.toInt).toList).toList
    
    println(inputSet.map(isValid(_)).map(x => { if (x == true) "YES" else "NO" }).mkString("\n"))
  }
}