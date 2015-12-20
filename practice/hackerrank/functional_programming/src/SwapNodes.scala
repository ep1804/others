import scala.collection.mutable.ListBuffer

object SwapNodes {

  case class Node(val id: Int, val left: Int, val right: Int)

  class Tree(val nodes: List[Node]) {
    val root = nodes(0)
    
    def swap(k: Int): Tree = {
      val ns: ListBuffer[Node] = new ListBuffer()
      ns ++= nodes
      
      def loop(x: Node, depth: Int): Unit ={
        if(x == null) return
        
        if(depth % k == 0)
          ns(x.id - 1) = Node(x.id, x.right, x.left)
        
        loop(if(x.left < 0) null else ns(x.left), depth + 1)
        loop(if(x.right < 0) null else ns(x.right), depth + 1)
      }
      
      loop(root, 1)
      
      new Tree(ns.toList)
    }
    
    def inorder: List[Int] = {
      val buf: ListBuffer[Int] = new ListBuffer()
      
      def loop(x: Node): Unit = {
        if(x == null) return
        
        loop(if(x.left < 0) null else nodes(x.left))
        buf += x.id
        loop(if(x.right < 0) null else nodes(x.right))
      }
      
      loop(root)
      buf.toList
    }
    
    override def toString: String = inorder.mkString(" ") 
  }

  def main(args: Array[String]): Unit = {
    
    val in = io.Source.stdin.getLines
    val N = in.next.toInt
    
    val nodes = (((1 to N) zip in.take(N).toList) map { x => { 
      val nums = x._2.trim.split("\\s+").map(_.toInt)
      new Node(x._1, nums(0) - 1, nums(1) - 1)
    }}).toList

    var tree = new Tree(nodes)
    
    val T = in.next.toInt    
    for(k <- in.take(T).map(_.toInt)){
      tree = tree.swap(k)
      println(tree)
    }
  }
}