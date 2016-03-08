package andes

object T0101 {

  def kSub(k: Int, nums: Array[Int]): Long = {

    // return n + (n - 1) + ... + 2 + 1   
    def cases(n: Long): Long = n * (n + 1) / 2

    // accumulation of 'nums' modular k
    lazy val sums: Stream[Int] = (nums(0) % k) #:: (nums.toStream.tail zip sums).map { case (a, b) => (a + b) % k }

    // counts of each modulus in 'sums'
    val counts = sums.toList.groupBy(x => x).map(x => (x._1, x._2.size.toLong))

    // number of subsequences that ends where sum(mod) is zero
    val res0 = cases(counts(0))

    // number of subsequences that ends where sum(mod) is not zero
    val ress = counts.filter(_._1 != 0).values.map(x => cases(x - 1)).sum

    res0 + ress
  }

  def main(args: Array[String]): Unit = {
    println(kSub(3, Array(1, 2, 3, 4, 1)))
  }

}