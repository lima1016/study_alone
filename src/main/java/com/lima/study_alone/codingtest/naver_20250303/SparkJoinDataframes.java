package com.lima.study_alone.codingtest.naver_20250303;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

public class SparkJoinDataframes {

  final static SparkSession spark = SparkSession.builder()
      .master("local[*]")
      .appName("SparkJoinDataframes")
      .getOrCreate();

  public static void main(String[] args) {
    transform(extractCouncils(), extractAvgPrice(), extractSalesVolume());
  }

  static Dataset<Row> extractCouncils() {
    Dataset<Row> district = spark.read()
        .option("header", "true")
        .csv("data/england_councils/district_councils.csv");

    Dataset<Row> london = spark.read()
        .option("header", "true")
        .csv("data/england_councils/london_boroughs.csv");

    Dataset<Row> metropolitan = spark.read()
        .option("header", "true")
        .csv("data/england_councils/metropolitan_districts.csv");

    Dataset<Row> unitary = spark.read()
        .option("header", "true")
        .csv("data/england_councils/unitary_authorities.csv");

    Dataset<Row> districtType = district.withColumn("council_type", lit("District Council"));
    Dataset<Row> londonType = london.withColumn("council_type", lit("London Borough"));
    Dataset<Row> metropolitanType = metropolitan.withColumn("council_type", lit("Metropolitan District"));
    Dataset<Row> unitaryType = unitary.withColumn("council_type", lit("Unitary Authoritie"));

    return districtType
        .union(londonType)
        .union(metropolitanType)
        .union(unitaryType);
  }

  static Dataset<Row> extractAvgPrice() {
    return spark.read()
        .option("header", "true")
        .csv("data/property_avg_price.csv")
        .select(
            col("local_authority").as("council")
            , col("avg_price_nov_2019").cast("double").as("avg_price_nov_2019")
        );
  }

  static Dataset<Row> extractSalesVolume() {
    return spark.read()
        .option("header", "true")
        .csv("data/property_sales_volume.csv")
        .select(
            col("local_authority").as("council")
            , col("sales_volume_sep_2019").cast("int").as("sales_volume_sep_2019")
        );
  }

  static Dataset<Row> transform(Dataset<Row> councilsDf, Dataset<Row> avgPriceDf, Dataset<Row> salesVolumeDf) {
    Dataset<Row> leftJoin = councilsDf.alias("councils").join(avgPriceDf.alias("avgPrice"), col("councils.council").equalTo(col("avgPrice.council")), "left_outer");
    Dataset<Row> result = leftJoin.join(salesVolumeDf.alias("salesVolume"), col("councils.council").equalTo(col("salesVolume.council")), "left_outer");

    return result.select(
        col("councils.council").as("council")
        , col("councils.county").as("county")
        , col("councils.council_type").as("council_type")
        , col("avgPrice.avg_price_nov_2019")
        , col("salesVolume.sales_volume_sep_2019")
    ).toDF("council", "county", "council_type", "avg_price_nov_2019", "sales_volume_sep_2019");
  }

}