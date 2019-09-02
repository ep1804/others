package sort

import org.scalatest._

class TestSorts extends FunSuite {

  test("insertion sort") {
    val arr = "bacd".split("")
    Sorts.insertionSort(arr)
    println("sorted: " + arr.mkString(" "))
    assert(arr.mkString("") == "abcd")
  }

}
