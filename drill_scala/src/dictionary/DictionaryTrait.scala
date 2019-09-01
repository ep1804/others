package dictionary

trait DictionaryTrait[K, V] {

  def put(key: K, value: V): Unit

  def get(key: K): Option[V]

  def delete(key: K): Boolean

  def inorder: Seq[(K, V)]

  override def toString: String = {
    "  " + inorder.map { case (k, v) => s"${k.toString}: ${v.toString}" }.mkString("\n  ")
  }
}
