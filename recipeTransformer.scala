import com.dataiku.dss.spark._
import org.apache.spark.ml.feature.{HashingTF, IDF, RegexTokenizer, StopWordsRemover}
import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._

val sparkConf    = DataikuSparkContext.buildSparkConf()
val sparkContext = new SparkContext(sparkConf)
val sqlContext   = new SQLContext(sparkContext)
val dkuContext   = DataikuSparkContext.getContext(sparkContext)

// Recipe inputs
val onlyMailContent = dkuContext.getDataFrame(sqlContext, "onlyMailContent")
    
val tokenizer = new RegexTokenizer().
  setInputCol("values").
  setOutputCol("words").
  setMinTokenLength(3).
  setGaps(false).
  setPattern("[a-zA-Z]+")

val stopWordsRemover = new StopWordsRemover().
  setInputCol(tokenizer.getOutputCol).
  setOutputCol("filtered").
  setStopWords(Array("the", "a", "", "in", "on", "at", "as", "not", "for")).
  setCaseSensitive(false)

val hashingTF = new HashingTF().
  setNumFeatures(1 << 10).
  setInputCol(tokenizer.getOutputCol).
  setOutputCol("wordToIndex")

val idf = new IDF().
  setMinDocFreq(4).
  setInputCol(hashingTF.getOutputCol).
  setOutputCol("tf_idf")

val stages = Array(tokenizer, stopWordsRemover, hashingTF, idf);
val pipeline = new Pipeline().setStages(stages);

val model = pipeline.fit(onlyMailContent)
val result = model.transform(onlyMailContent)

// Recipe outputs
dkuContext.save("enron_out", result)
