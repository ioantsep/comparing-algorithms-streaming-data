import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamContextEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import com.yahoo.labs.samoa.instances.Instance;
import moa.classifiers.Classifier;
import moa.classifiers.bayes.NaiveBayes;
import moa.core.Example;
 
public class TrainTestRun {
 	
 	public static void main(String[] args) throws Exception {
 		
 		// just one classifier here, so parallelism is 1
 		StreamExecutionEnvironment env = StreamContextEnvironment.createLocalEnvironment(1);
 		
 		// create the generator
 		DataStreamSource<Example<Instance>> rrbfSource = env.addSource(new RRBFSource());
 		
 		// split in train and test
 		SplitStream<Example<Instance>> trainAndTestStream = rrbfSource.split(new RandomSamplingSelector(0.02));
 		
 		DataStream<Example<Instance>> testStream = trainAndTestStream.select(RandomSamplingSelector.TEST);
 		
		DataStream<Example<Instance>> trainStream =	trainAndTestStream.select(RandomSamplingSelector.TRAIN);
 		
 		// create one classifier
 		SingleOutputStreamOperator<Classifier> classifier = trainStream.process(new LearningProcessFunction(NaiveBayes.class, 1000)); // you can use any algorithm you want 
 		
 		// predict on the test stream, update the classifier when there is a new version
 		testStream.connect(classifier)
 			        .flatMap(new ClassifyAndUpdateClassifierFunction())
 			        .countWindowAll(2_000).aggregate(new PerformanceFunction()) // aggregate, change number for testing (2_000, 5_000, 10_000 for 100K, 250K, 500K) instances  
 			        .print(); // show results
 			
 		
 		// fire execution
 		env.execute("MOA Flink");
 	}
 
 }
