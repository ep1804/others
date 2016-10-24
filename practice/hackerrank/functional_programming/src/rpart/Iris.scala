package rpart

import scala.collection.mutable.ListBuffer

object Iris {

  type Data = Array[(Array[Double], Boolean)]
  type Rule = Array[Double] => Boolean

  class Node

  def rpart(data: Data, rules: List[Rule], cp: Double): Node = {
    new Node()
  }

  def main(args: Array[String]): Unit = {
    val file = System.getProperty("user.dir") + """\src\rpart\iris.csv"""
    val lines = io.Source.fromFile(file).getLines
    val vectors = lines.map(_.trim.split(",")).toArray
    
    // Data: Array of (explanatory variables, response variable)
    val data = vectors.map{case v => (v.take(4).map(_.toDouble), if(v(4) == "setosa") false else true)}.toArray
    
    // Rules
    val thresholds = data.map(_._1).transpose.map({x => 
      val min = x.min
      val max = x.max
      min to max by (max - min) / 9
    })
    val rules = new ListBuffer[Rule]
    for(i <- 0 until 4){
      for(th <- thresholds(i)){
        val ftn1 = (x: Array[Double]) => if(x(i) >= th) true else false
        val ftn2 = (x: Array[Double]) => if(x(i) < th) true else false
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