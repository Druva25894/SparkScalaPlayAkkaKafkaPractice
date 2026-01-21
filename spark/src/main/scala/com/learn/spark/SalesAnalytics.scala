package com.learn.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object SalesAnalytics {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("SalesAnalytics")
      .master("local[*]")
      .getOrCreate()

    val df = spark.read
      .option("header","True")
      .option("InferSchema","True")
      .csv("spark/src/main/resources/sales.csv")
    println("====Input Data ====")
    df.show()
    val totalByUser = df
      .groupBy("userId")
      .agg(sum("amount").as("totalAmount"))
    println("=== Total Amount By User ===")
    totalByUser.show()

    val totalByProduct = df
      .groupBy("product")
      .agg(sum("amount").as("totalSales"))

    println("=== Total Sales By Product ===")
    totalByProduct.show()

    val topUser = totalByUser
      .orderBy(desc("totalAmount"))
      .limit(1)

    println("=== Top Spending User ===")
    topUser.show()

    spark.stop()
  }


}
