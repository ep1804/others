package dictionary

import scala.collection.mutable.ArrayBuffer

object BinarySearchTree {

  class Tree extends DictionaryTrait[K, V] {

    class Node

    object Leaf extends Node

    case class Stem(key: K, var value: V, var left: Node = Leaf, var right: Node = Leaf) extends Node

    private var root: Node = Leaf

    override def put(key: K, value: V): Unit = {

      def put(node: Node, key: K, value: V): Node = node match {
        case Leaf =>
          Stem(key, value)
        case s@Stem(k, _, l, r) =>
          if (key < k) s.left = put(l, key, value)
          else if (key > k) s.right = put(r, key, value)
          else s.value = value
          s
      }

      root = put(root, key, value)
    }

    private def get(node: Node, key: K): Node = node match {
      case Leaf =>
        Leaf
      case s@Stem(k, _, l, r) =>
        if (key < k) get(l, key)
        else if (key > k) get(r, key)
        else s
    }

    override def get(key: K): Option[V] = {
      get(root, key) match {
        case Leaf => None
        case Stem(_, v, _, _) => Some(v)
      }
    }

    override def delete(key: K): Boolean = ???

    override def inorder: Seq[(K, V)] = {

      val res = ArrayBuffer[(K, V)]()

      def trav(node: Node): Unit = node match {
        case Leaf => Unit
        case Stem(k, v, l, r) =>
          trav(l)
          res += ((k, v))
          trav(r)
      }

      trav(root)

      res
    }
  }

  def main(args: Array[String]): Unit = {
    val lines = io.Source.stdin.getLines

    val tree = new Tree()

    lines.foreach { line =>
      val Array(k, v) = line.trim.split("\\s+")
      tree.put(k.toInt, v)
    }

    println("full tree:\n" + tree)
    println("get 6: " + tree.get(6))
    println("get 11: " + tree.get(11))
    //    println("delete 6: " + tree.delete(6))
    //    println("delete 11: " + tree.delete(11))
    //    println("full tree (after delete):\n" + tree)
  }

}
