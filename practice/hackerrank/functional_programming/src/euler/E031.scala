package euler

object CoinSums {

  def div = 1000000007

  val cache3 = collection.mutable.Map[Int, Long]()
  val cache4 = collection.mutable.Map[Int, Long]()
  val cache5 = collection.mutable.Map[Int, Long]()
  val cache6 = collection.mutable.Map[Int, Long]()
  val cache7 = collection.mutable.Map[Int, Long]()
  val cache8 = collection.mutable.Map[Int, Long]()

  // combinations with one coin: 1p
  def coin1(n: Int): Long = 1L

  // combinations with two coins: 1p, 2p
  def coin2(n: Int): Long = 1L + n / 2

  // combinations with three coins: 1p, 2p, 5p
  def coin3(n: Int): Long =
    if (n < 5) coin2(n)
    else if (cache3.contains(n)) cache3(n)
    else {
      val res = (coin2(n) + coin3(n - 5)) % div
      cache3.put(n, res)
      res
    }

  // combinations with four coins: 1p, 2p, 5p, 10p
  def coin4(n: Int): Long =
    if (n < 10) coin3(n)
    else if (cache4.contains(n)) cache4(n)
    else {
      val res = (coin3(n) + coin4(n - 10)) % div
      cache4.put(n, res)
      res
    }

  // combinations with five coins: 1p, 2p, 5p, 10p, 20p
  def coin5(n: Int): Long =
    if (n < 20) coin4(n)
    else if (cache5.contains(n)) cache5(n)
    else {
      val res = (coin4(n) + coin5(n - 20)) % div
      cache5.put(n, res)
      res
    }

  // combinations with 6 coins: 1p, 2p, 5p, 10p, 20p, 50p
  def coin6(n: Int): Long =
    if (n < 50) coin5(n)
    else if (cache6.contains(n)) cache6(n)
    else {
      val res = (coin5(n) + coin6(n - 50)) % div
      cache6.put(n, res)
      res
    }

  // combinations with 7 coins: 1p, 2p, 5p, 10p, 20p, 50p, 100p
  def coin7(n: Int): Long =
    if (n < 100) coin6(n)
    else if (cache7.contains(n)) cache7(n)
    else {
      val res = (coin6(n) + coin7(n - 100)) % div
      cache7.put(n, res)
      res
    }

  // combinations with 8(all) coins: 1p, 2p, 5p, 10p, 20p, 50p, 100p, 200p
  def coin8(n: Int): Long =
    if (n < 200) coin7(n)
    else if (cache8.contains(n)) cache8(n)
    else {
      val res = (coin7(n) + coin8(n - 200)) % div
      cache8.put(n, res)
      res
    }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.trim.toInt
    val ns = in.take(t).map(_.trim.toInt)

    ns foreach { n => println(coin8(n)) }
  }
}