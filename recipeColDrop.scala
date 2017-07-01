import com.dataiku.dss.spark._
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._

val sparkConf    = DataikuSparkContext.buildSparkConf()
val sparkContext = new SparkContext(sparkConf)
val sqlContext   = new SQLContext(sparkContext)
val dkuContext   = DataikuSparkContext.getContext(sparkContext)

// Recipe inputs
val preprocessed_subset_filtered = dkuContext.getDataFrame(sqlContext, "preprocessed_subset_filtered")

val result = preprocessed_subset_filtered.select("values")

    
// Recipe outputs
dkuContext.save("onlyMailContent", result)
