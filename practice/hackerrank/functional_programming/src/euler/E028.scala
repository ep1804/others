package euler

object E028 {
  // the series for sum of 4 corners at each level:
  // a1 = 1
  // a2 = 24
  // ... 
  // an = 16n^2 - 28n + 16
  //
  // the series for sum of diagonals:
  // s1 = 1
  // s2 = s1 + a1
  // ...
  // sn = s_n-1 + an
  // 
  // sn = s1 + Sigma(i from 2 to n){ai}
  //
  // calculation by Faulhaber's formula, https://en.wikipedia.org/wiki/List_of_mathematical_series
  //
  // sn = ...
  // (for n >= 2)
  def diagonal(n: BigInt): BigInt = {
    if (n == 1) 1
    else n * (n + 1) * (2 * n + 1) / 3 * 8 - n * (n + 1) * 14 + n * 16 - 3
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val t = in.next.toInt
    val ns = in.take(t).map(n => (BigInt(n) + 1) / 2)

    val div = BigInt("1000000007")
    
    ns foreach { n => println(diagonal(n) % div) }
  }

}