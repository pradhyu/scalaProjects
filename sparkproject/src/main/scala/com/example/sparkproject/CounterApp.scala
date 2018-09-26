package com.example.sparkProject

import org.apache.spark.{SparkConf, SparkContext}
/* sbt run inputFile threshodInteger
 * select BruteForceWordCounter
 *  example: run "/Users/pkshrestha/workspace/scalaProjects/sparkproject/src/test/resources/2008.csv" 100
 * * in cmd line use sbt "run /Users/pkshrestha/workspace/scalaProjects/sparkproject/src/test/resources/example.txt 1"
 */
object BruteForceWordCounter extends App {
  // create Spark context with Spark configuration
  val conf = new SparkConf()
    .setMaster("local")
    .setAppName("BruteForce Count")
  val sc = new SparkContext(conf)

  // get threshold
  val threshold = args(1).toInt

  // read in text file and split each document into words
  val tokenized = sc.textFile(args(0)).flatMap(_.split(","))

  // count the occurrence of each word
  val wordCounts = tokenized.map((_, 1)).reduceByKey(_ + _)

  // filter out words with fewer than threshold occurrences
  val filtered = wordCounts.filter(_._2 >= threshold)

  // count characters
  //  val charCounts = filtered.flatMap(_._1.toCharArray).map((_, 1)).reduceByKey(_ + _)


  System.out.println("Word count:>>> ")

  filtered.collect().foreach{ it => println(s">>>$it")}
}
