package andes

import scala.util.Random

object GenTest {

  def shuffle(ns: Seq[Int]): Seq[Int] = {
    val ss = collection.mutable.ArrayBuffer.concat[Int](ns)

    def exch(i: Int, j: Int): Unit = {
      val tmp = ss(i)
      ss(i) = ss(j)
      ss(j) = tmp
    }

    for (i <- 1 until ns.size) {
      val j = Random.nextInt(i + 1)
      if (i != j) exch(i, j)
    }
    
    ss.toArray
  }

  def gen(canceled: Boolean): (Int, Int, Seq[Int]) = {
    val n = 1 + Random.nextInt(200)
    val k = 1 + Random.nextInt(n)
    val as = collection.mutable.ListBuffer[Int]()
    as += 0
    
    val earlys = math.max(if(canceled) Random.nextInt(k - 1) else (k - 1 + Random.nextInt(n - k)), 1) 
    
    as ++= (1 to earlys).map(_ => -1 - Random.nextInt(1000))
    as ++= (1 to (n - earlys - 1)).map(_ => 1 + Random.nextInt(1000))
    
    (n, k, shuffle(as))
  }

  def main(args: Array[String]): Unit = {
    val ans = Seq(true, false, true, false, true)

    val qs = ans.map(gen(_))
    
    println(ans.size)
    qs.foreach{ case(n, k, as) => 
      println(n + " " + k)
      println(as.mkString(" "))
    }
    
  }
}