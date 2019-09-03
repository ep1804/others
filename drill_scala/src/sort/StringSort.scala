package sort

object StringSort {

  def threeWayQuickSort(strings: Array[String]): Unit = {

    def charAt(x: String, index: Int): Char = if (x.length <= index) Char.MinValue else x.charAt(index)

    def swap(a: Int, b: Int): Unit = {
      val x = strings(a)
      strings(a) = strings(b)
      strings(b) = x
    }

    def partition(from: Int, until: Int, radix: Int): (Int, Int, Char) = {

      var p1 = from + 1
      var p2 = from + 1 // used as index
      var p3 = until - 1

      val pivot = charAt(strings(from), radix)

      while (p2 <= p3) {
        val c = charAt(strings(p2), radix)
        if (c < pivot) {
          swap(p1, p2)
          p1 += 1
          p2 += 1
        } else if (c > pivot) {
          swap(p2, p3)
          p3 -= 1
        } else {
          p2 += 1
        }
      }

      swap(from, p1 - 1)
      (p1 - 1, p2, pivot)
    }

    def sort(from: Int, until: Int, radix: Int): Unit = {
      if (from >= until) return

      val (p2, p3, pivot) = partition(from, until, radix)

      sort(from, p2, radix)
      if (pivot > Char.MinValue) sort(p2, p3, radix + 1)
      sort(p3, until, radix)
    }

    sort(0, strings.length, 0)
  }


  def main(args: Array[String]): Unit = {
    val strings = io.Source.stdin.getLines.filter(_.nonEmpty).toArray

    println("words: \n" + strings.map("  " + _).mkString("\n"))

    threeWayQuickSort(strings)

    println("sorted: \n" + strings.map("  " + _).mkString("\n"))
  }

}
