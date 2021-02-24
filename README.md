# Comparing algorithms on streaming data
The goal of this project is to evaluate the accuracy of 9 classification algorithms that are applied on streaming data. 

First, a real-time data pipeline was created, that:
- [x] creates data source with a RandomRBF generator
- [x] uses the Apache Flink to split data to 80% training and 20% testing sets 
- [x] attaches the Massive Online Analysis(MOA) open source framework for applying one by one the classification algorithms, 
- [x] gives the results of accuracy for every applied algorithm.

Having the results, a comparison of the 9 algorithms accuracy take place. 


## **Tools - Frameworks** ##
- Virtual Machine: VirtualBox v5.2.26, 
- Hardware settings: 2 cores, 6GB RAM, 12GB HDD, 
- OS: Ubuntu 18.04 (Debian Linux) 
- Oracle Java v1.80, 
- Apache Maven v3.6.0,
- Apache Flink v1.7.2,


## **Algorithms** ##
- NaïveBayes,
- Decision Stump,
- Hoeffding Tree,
- HoeffdingOption Tree, 
- AdaHoeffdingOption Tree, 
- HoeffdingAdaptive Tree, 
- OzaBag,  
- OzaBoost, 
- OzaBagADWIN


## Dataset Information ##
- The RandomRBF Generator generates a random radial basis function data in form of instance, which have 10 numeric attributes and 2 classes(class1, class2). The total number of instaneces is 10M per algorithm.

![RBF_data](https://github.com/ioantsep/comparing-algorithms-streaming-data/blob/main/images/RBF_data.png)


## Pipeline Architecture ##

![pipeline](https://github.com/ioantsep/comparing-algorithms-streaming-data/blob/main/images/pipeline3.png)



## **Data Flow** ##
- __RandomRBF generator:__ generate data (10M instances) 

- __Data Flow 1:__ send data to Apache Flink, where the data splits to training and testing set and applied to MOA 

- __Results:__ print results


## **Coding** ##
- TrainTestRun.java code: [TrainTestRun.java](https://github.com/ioantsep/comparing-algorithms-streaming-data/blob/main/coding/TrainTestRun.java)
- RRBFSource.java code: [RRBFSource.java](https://github.com/ioantsep/comparing-algorithms-streaming-data/blob/main/coding/RRBFSource.java)
- LearningProcessFunction.java: [LearningProcessFunction.java](https://github.com/ioantsep/comparing-algorithms-streaming-data/blob/main/coding/LearningProcessFunction.java)
- PerformanceFunction.java code: [PerformanceFunction.java](https://github.com/ioantsep/comparing-algorithms-streaming-data/blob/main/coding/PerformanceFunction.java)
- ClassifyAndUpdateClassifierFunction.java code: [ClassifyAndUpdateClassifierFunction.java](https://github.com/ioantsep/comparing-algorithms-streaming-data/blob/main/coding/ClassifyAndUpdateClassifierFunction.java)
- RandomSamplingSelector.java: [RandomSamplingSelector.java](https://github.com/ioantsep/comparing-algorithms-streaming-data/blob/main/coding/RandomSamplingSelector.java)


## **Build, Provision and Deploy the Project** ##

For every different algorithm:

1. Starting maven in terminal 1: 
	```
	cd /opt/maven-projects/
	mvn exec:java -Dexec.mainClass="moa.flink.traintest.TrainTestRun"
	```
	![running_algor](https://github.com/ioantsep/comparing-algorithms-streaming-data/blob/main/images/running_algor.png)	
	
## **Results** ##
For every algorithm we take the accuracy for _80K, 200K and 400K_ training sets, and we come to a conclusion that:
- the algorithm **OzaBagAdwin** gives the best accuracy for the _80K training sets (the **OzaBag** accuracy is very close to the winner)
- the algorithm **OzaBag** gives the best accuracy for the _200K training sets (the **OzaBagAdwin** accuracy is very close to the winner)
- the algorithm **OzaBag** gives the best accuracy for the _400K training sets (the **OzaBagAdwin** accuracy is very close to the winner)

![80K](https://github.com/ioantsep/comparing-algorithms-streaming-data/blob/main/images/80%CE%9A.png)
![200K](https://github.com/ioantsep/comparing-algorithms-streaming-data/blob/main/images/200%CE%9A.png)
![400K](https://github.com/ioantsep/comparing-algorithms-streaming-data/blob/main/images/400%CE%9A.png)








## **Useful Links** ##
- MOA, https://moa.cms.waikato.ac.nz/
- Apache Flink, https://flink.apache.org/
