package sort

import org.scalatest._

class TestSorts extends FunSuite {

  test("insertion sort") {
    val arr = "bacd".split("")
    CompareSort.insertionSort(arr)
    println("sorted: " + arr.mkString(" "))
    assert(arr.mkString("") == "abcd")
  }

}
