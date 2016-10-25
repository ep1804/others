package rpart

import scala.collection.mutable.ListBuffer

object Iris {

  type X = Array[Double]
  type Data = Array[(X, Boolean)]
  type Rule = X => Boolean

  class Node(d: Data) {
    private val groups = d.groupBy(_._2)
    val n0 = groups.getOrElse(false, Array()).size
    val n1 = groups.getOrElse(true, Array()).size
  }

  class LeafNode(d: Data) extends Node(d) {
    override def toString(): String = {
      "LeafNode(" + n1 + "/" + (n0 + n1) + ")"
    }
  }

  class StemNode(d: Data, rule: Rule, left: Node, right: Node) extends Node(d) {
    override def toString(): String = {
      "StemNode(" + n1 + "/" + n0 + n1 + ", " + left + ", " + right + ")"
    }
  }

  def rpart(data: Data, rules: List[Rule], cp: Double = 0.2): Node = {

    def impurity(d0: Data): Double = {
      val len = d0.length.toDouble
      val a = d0.count(_._2).toDouble / len
      val b = d0.count(!_._2).toDouble / len
      -a * Math.log(a) - b * Math.log(b)
    }

    def informationGain(d0: Data, rule: Rule) = {
      val entropy0 = impurity(d0)

      // probability of rule pass
      val p1 = d0.count { case (x, _) => rule(x) }.toDouble / d0.length.toDouble

      // compute information gain after split by rule
      val parts = d0.groupBy { case (x, _) => !rule(x) }
      val entropyLeft = impurity(parts.getOrElse(true, Array()))
      val entropyRight = impurity(parts.getOrElse(false, Array()))
      val entropy1 = entropyLeft * p1 + entropyRight * (1 - p1)
      entropy0 - entropy1
    }

    def loop(d0: Data, rules: List[Rule]): Node = {
      
      if(d0.length == 0){
        new LeafNode(d0)
      }
      
      val gains = rules.map(x => informationGain(d0, x))

      if (gains.min < cp) {
        new LeafNode(d0)
      } else {
        val maxIndex = gains.zipWithIndex.maxBy(_._1)._2
        val parts = d0.groupBy { case (x, _) => !rules(maxIndex)(x) }
        new StemNode(d0, rules(maxIndex),
          loop(parts.getOrElse(true, Array()), rules.take(maxIndex) ++ rules.drop(maxIndex + 1)),
          loop(parts.getOrElse(false, Array()), rules.take(maxIndex) ++ rules.drop(maxIndex + 1)))
      }
    }

    loop(data, rules)
  }

  def main(args: Array[String]): Unit = {
    val file = System.getProperty("user.dir") + """\src\rpart\iris.csv"""
    val lines = io.Source.fromFile(file).getLines
    val vectors = lines.map(_.trim.split(",")).toArray

    // Data: Array of (explanatory variables, response variable)
    val data = vectors.map { case v => (v.take(4).map(_.toDouble), if (v(4) == "versicolor") true else false) }.toArray

    // Rules
    val thresholds = data.map(_._1).transpose.map({ x =>
      val min = x.min
      val max = x.max
      min to max by (max - min) / 9
    })
    val rules = new ListBuffer[Rule]
    for (i <- 0 until 4) {
      for (th <- thresholds(i)) {
        val ftn1 = (x: Array[Double]) => if (x(i) >= th) true else false
        val ftn2 = (x: Array[Double]) => if (x(i) < th) true else false
        rules += ftn1
        rules += ftn2
      }
    }

    println(data.toList)
    println(thresholds.toList)
    println(rules)

    // Test rule functions
    println(rules(0)(Array(4.4, 0, 0, 0)))
    println(rules(0)(Array(4.2, 0, 0, 0)))
    println(rules(1)(Array(4.4, 0, 0, 0)))
    println(rules(1)(Array(4.2, 0, 0, 0)))
    println(rules(18)(Array(0, 2.1, 0, 0)))
    println(rules(18)(Array(0, 1.9, 0, 0)))
    println(rules(19)(Array(0, 2.1, 0, 0)))
    println(rules(19)(Array(0, 1.9, 0, 0)))

    // Test rpart

    print(rpart(data, rules.toList, 0.2))
  }
}