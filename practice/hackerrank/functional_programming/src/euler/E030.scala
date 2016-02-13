package euler

object DigitNthPowers {

  def solve(n: Int): Long = {

    def check(k: Long): Boolean =
      (k.toString.toCharArray.map(_ - '0').map(math.pow(_, n)).sum + 0.0001).toLong == k

    def limitLoop(x: Double, f: Double => Double): Long =
      if (f(x) < x) return x.toLong
      else limitLoop(f(x) + 1, f)

    val d1 = math.pow(9, n)
    val limit = limitLoop(100, x => (1.0 + math.log10(x)) * d1)

    var res = 0L;

    def loop(k: Long): Unit = {
      if (k > limit) return

      if (check(k)) res = res + k
      loop(k + 1)
    }

    loop(3)
    res;
  }

  def main(args: Array[String]): Unit = {
    val in = io.Source.stdin.getLines
    val n = in.next.toInt

    println(solve(n))
  }
}