package andes

object DataTypes {
  def typeString(x: Any): String = x match {
    case z: Boolean => "Premitive : boolean"
    case c: Char => "Premitive : char"
    case i: Int => "Premitive : int"
    case d: Double => "Premitive : double"
    case l: String => "Referenced : String"

  }

  def main(args: Array[String]): Unit = {

    val qs = List(5.35, 'a', false, 100, "I am a code monkey", true, 17.3, 'c', "derp")

    println(qs.map(typeString(_)).mkString("\n"))

    def testAnyVal[T: Manifest](t: T) = manifest[T] <:< manifest[AnyVal]
  }
}