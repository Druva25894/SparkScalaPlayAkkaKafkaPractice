package com.learn.dp

object Fibonacci {

  //simple recursion
  def fibRecursive(n: Int): Int = {
    if (n<=1) n else fibRecursive(n-1) + fibRecursive(n-2)
  }

  //memorization
  def fibMemo(n: Int): Int = {
    def helper(n: Int, memo: Map[Int, Int]): (Int,Map[Int,Int]) = {
      println(s"values of a,memo -> $n,$memo")
      memo.get(n) match {
        case Some(value) => (value, memo)
        case None =>
          val (res, updatedMemo) = if (n <= 1) (n, memo)
          else {
            val (a, m1) = helper(n-1, memo)
            println(s"values of a,m1 -> $a,$m1,$memo")
            val (b, m2) = helper(n-2, m1)
            (a+b, m2)
          }
          (res,updatedMemo +(n -> res))

      }
    }
    helper(n,Map.empty)._1
  }

  def main(args: Array[String]): Unit = {
    println(s"fibRecursive(6) = ${fibRecursive(6)}")
    println(s"fibMemo(10) = ${fibMemo(10)}")
  }

}