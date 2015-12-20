import scala.collection.mutable.ListBuffer

object SwapNodes2 {

  case class Node(val value: Int, val left: Node, val right: Node)

  class Tree(val root: Node) {

    def swap(k: Int): Tree = {

      def loop(x: Node, depth: Int): Node = {
        if (x == null) return null

        if (depth % k == 0)
          Node(x.value, loop(x.right, depth + 1), loop(x.left, depth + 1))
        else
          Node(x.value, loop(x.left, depth + 1), loop(x.right, depth + 1))
      }

      new Tree(loop(root, 1))
    }

    def inorder: List[Int] = {
      val buf: ListBuffer[Int] = new ListBuffer()

      def loop(x: Node): Unit = {
        if (x == null) return

        loop(x.left)
        buf += x.value
        loop(x.right)
      }

      loop(root)
      buf.toList
    }

    override def toString: String = inorder.mkString(" ")

  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val N = in.next.toInt

    val nodes = (((1 to N) zip in.take(N).toList) map { x =>
      {
        val nums = x._2.trim.split("\\s+").map(_.toInt)
        Tuple3(x._1, nums(0), nums(1))
      }
    }).toList

    def build(n: Tuple3[Int, Int, Int]): Node = n match {
      case (x, -1, -1) => Node(x, null, null)
      case (x, l, -1) => Node(x, build(nodes(l - 1)), null)
      case (x, -1, r) => Node(x, null, build(nodes(r - 1)))
      case (x, l, r) => Node(x, build(nodes(l - 1)), build(nodes(r - 1)))
    }

    val root = build(nodes(0))

    var tree = new Tree(root)

    val T = in.next.toInt
    for (k <- in.take(T).map(_.toInt)) {
      tree = tree.swap(k)
      println(tree)
    }

  }
}