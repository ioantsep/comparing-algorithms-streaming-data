# Comparing algorithms on streaming data
The goal of this project is to evaluate the accuracy of 9 classification algorithms that are applied on streaming data. 

For this purpose a real-time data pipeline was created, that:

- [x] creates data source with a RandomRBF generator
- [x] uses the Apache Flink to split data to 80% training and 20% testing sets 
- [x] attaches the Massive Online Analysis (MOA) open source framework for data stream mining and applying one by one classification algorithms, 
- [x] gives the results of accuracy for every applied algorithm.

After that a comparisson of the 9 algorithms accuracy take place.  

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
- The RandomRBF Generator generates a random radial basis function data in form of instance, which have 10 attributes and 2 classes(class1, class2). The total number of instaneces is 10M per algorithm.

![IoT_simulation](https://github.com/ioantsep/realtime-pipeline-kafka-flink/blob/main/images/IoT_simul.png)


## Pipeline Architecture ##

![architect](https://github.com/ioantsep/realtime-pipeline-kafka-flink/blob/main/images/architect_system.png)



## **Data Flow** ##
- __IoT sensor simulator:__ data from the sensor, Apache Kafka's Producer

- __Data Flow 1:__ send to Apache Kafka

- __Data Flow 2:__ send to Apache Flink, Apache Kafka's Consumer

- __Data Flow 3:__ send to Apache Cassandra

- __Data Flow 4:__ send to Elasticsearch

- __Visualize__: using Kibana


## **Coding** ##
- TrainTestRun.java code: [TrainTestRun.java](https://github.com/ioantsep/realtime-pipeline-kafka-flink/blob/main/coding/sendtosink.java)
- RRBFSource.java code: [RRBFSource.java](https://github.com/ioantsep/realtime-pipeline-kafka-flink/blob/main/coding/DataGenerator.java)
- LearningProcessFunction.java
- PerformanceFunction.java 
- ClassifyAndUpdateClassifierFunction.java


## **Build, Provision and Deploy the Project** ##
1. Starting Zookeeper(Kafka) in terminal 1: 
	```
	cd /opt/kafka
	bin/zookeeper-server-start.sh config/zookeeper.properties
	```
