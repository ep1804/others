# Euler Project Memo

 - 2: fibonacci 
 - 3: primes, largest prime factor 
 - 5: gcd, lcm 
 - 6: Stream sum, diff 
 - 9: for comprehension 
 - 10: primes, sum 
 - 12, 21, 23: factorization
 - 13: BigInt
 - 15: lattice, caching
 - 18: foldLeft, zip
 - 19: Zeller's congruence, Java GregorianCalendar
 - 20: factorial  
 - 25: fibonacci closed-form, golden ratio
 - 28: Faulhaber's formula for sum of polynomial series
 - 29: factorization again
 - 30: estimating crossing point of two monotone increase functions (preliminary)
 - 31: recursion and foldLeft!

 
# Generating Streams
 
```scala
  lazy val twoPowers : Stream[Int] = 1 #:: twoPowers.map(_ * 2)
```

```scala
  lazy val fibs: Stream[Int] = 1 #:: 2 #:: fibs.zip(fibs.tail).map { case (a, b) => a + b }
```


 
# Integer Maths
 
```scala
  lazy val fibs: Stream[Int] = 1 #:: 2 #:: fibs.zip(fibs.tail).map { case (a, b) => a + b }
```
 
```scala
  lazy val primes: Stream[Int] = 
      2 #:: Stream.from(3, 2).filter(x => primes.takeWhile(_ <= math.sqrt(x)).forall(x % _ != 0))
```
 
```scala
  def gcd(a: Int, b: Int): Int = 
    if(b == 0) a 
    else if(a > b) gcd(b, a % b)
    else gcd(a, b % a)
  
  def lcm(a: Int, b: Int): Int = a * b / gcd(a, b)
```

```scala
  def factorize(n: Int): Seq[(Int, Int)] = {

    def divRep(n: Int, d: Int): (Int, Int) = {
      @tailrec
      def loop(n: Int, count: Int): (Int, Int) = {
        if (n % d != 0) (n, count)
        else loop(n / d, count + 1)
      }
      loop(n, 0)
    }

    val res = ListBuffer[(Int, Int)]()

    @tailrec
    def loop(n: Int): Unit = {

      if (n == 1) return

      val ps = primes.takeWhile(_ <= math.sqrt(n)).filter(n % _ == 0) // small prime factors

      if (ps.size == 0) {
        res += ((n, 1))
      } else {
        val next = ps.foldLeft(n) {
          case (x, p) =>
            val (nx, pow) = divRep(x, p)
            res += ((p, pow))
            nx
        }
        loop(next)
      }
    }

    loop(n)
    res
  }

```
