# DataScienceStudioPipeline

In this repository we guide the reader in installing Data Science Studio and present him a construction of a pipeline by example.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites and Installation

Dataset used: [Enron](https://www.cs.cmu.edu/~./enron/)

You need to install Data Science Studio (DSS) from their [site](https://www.dataiku.com/dss/trynow/).
Follow the instruction they give you and activate the free 2 weeks trial in order to run the pipeline on Spark.

You need also to install Spark and configure DSS to point on it following those [instructions](https://doc.dataiku.com/dss/latest/spark/installation.html).
DSS supports Spark versions from 1.6 to 2.0, 2.1 is in beta and I do not recommend it for this project because it simply won't work.

For running DSS type in the console the following command

```
./bin/dss start
```
And go on your browser at http://youIp:11000/, then login with your account. Default administrator password is 

```
admin:admin
```
After you logged in, create a new project and start adding your dataset. You need to preprocess it first using this [notebook](https://github.com/Menion93/ReadFilesRecursively) in order to import it without any problems.
When you are done, it is the time to create the flow. Go in the flow chart and design it as represented below

![alt](https://github.com/Menion93/DataScienceStudioPipeline/blob/master/Screenshot1.png)

The first icon and the other that are similar are dataset. You can select the dataset and add a new recipie or visual transformation.
The second icon is a filter, you need to filter the dataset that you have imported for columns[0]=="content" and columns[1] not null.
The other 3 icons are all Spark-Scala code recipes, in the following orders: [recipeColDrop](https://github.com/Menion93/DataScienceStudioPipeline/blob/master/recipeColDrop.scala), [recipeTransformer](https://github.com/Menion93/DataScienceStudioPipeline/blob/master/recipeTransformer.scala) and [recipeColDrop2](https://github.com/Menion93/DataScienceStudioPipeline/blob/master/recipeColDrop2.scala)
Now you can execute and save your results in hdfs or in the local FS.

## Authors

* **Andrea Salvoni** - [Menion93](https://github.com/Menion93/)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

