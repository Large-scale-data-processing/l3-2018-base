package ml.lsdp.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.mllib.random.RandomRDDs._
import org.apache.spark.rdd.RDD
import org.slf4j.{Logger, LoggerFactory}

object Demo {

def main(args: Array[String]): Unit ={

  val log = LoggerFactory.getLogger(this.getClass)

  val spark = SparkSession
    .builder
    .appName("Spark Demo")
    .getOrCreate()

  import spark.implicits._

  val normalDataSet = normalRDD(spark.sparkContext, 10000000).toDS()

  log.info(s"${normalDataSet.summary().rdd.map(r => (r(0), r(1))).collect().toMap}")


  val counts1: RDD[(Int, Long)] =
    normalDataSet.map(n => ((n * 100.0) % 10).toInt).map(n => (n, 1L)).rdd.aggregateByKey(0L)(seqOp = (agg, c) => agg+c, combOp = (c1, c2) => c1+c2)

  log.info(s"${counts1.collect().toMap}")



  val counts2: collection.Map[Int, Long] =
    normalDataSet.map(n => ((n * 100.0) % 10).toInt).rdd.countByValue()

  log.info(s"$counts2")


  val mean = normalDataSet.rdd.mean()
  val stdDev = normalDataSet.rdd.stdev()
  val max = normalDataSet.rdd.max()
  val min = normalDataSet.rdd.min()

  log.info(s"Mean: $mean, stdDev: $stdDev, max: $max, min $min")
}

}
