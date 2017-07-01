import com.dataiku.dss.spark._
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._

val sparkConf    = DataikuSparkContext.buildSparkConf()
val sparkContext = new SparkContext(sparkConf)
val sqlContext   = new SQLContext(sparkContext)
val dkuContext   = DataikuSparkContext.getContext(sparkContext)

// Recipe inputs
val enron_out = dkuContext.getDataFrame(sqlContext, "enron_out")
val result = enron_out.select("tf_idf")

// Recipe outputs
dkuContext.save("enron_out_colDrop", result)
