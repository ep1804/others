package euler

import collection.mutable.Map

object CoinSums2 {

  def div = 1000000007

  // given added coin and previous counter function, build new counter function 
  // e.g.
  //  newCoin = 5
  //  base counter = counter with two coins: 1, 2
  //  return = new counter with three coins: 1, 2, 5
  def genCounter(newCoin: Int, base: Int => Long, cache: Map[Int, Long]): Int => Long = {

    def ftn(n: Int): Long = {
      if (n < newCoin) base(n)
      else if (cache.contains(n)) cache(n)
      else {
        val res = (base(n) + ftn(n - newCoin)) % div
        cache.put(n, res)
        res
      }
    }

    ftn
  }

  def solve(coins: List[Int], ns: Iterator[Int]): Iterator[Long] = {

    // counter function with two coins: 1p, 2p
    def coin2(n: Int): Long = 1L + n / 2

    // counter function with all 8 coin types    
    def coin8 = coins.drop(2).foldLeft(coin2 _) {
      case (base, newCoin) => genCounter(newCoin, base, Map[Int, Long]())
    }

    ns map coin8
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.trim.toInt
    val ns = in.take(t).map(_.trim.toInt)

    val coins = List(1, 2, 5, 10, 20, 50, 100, 200)

    solve(coins, ns) foreach println
  }
}