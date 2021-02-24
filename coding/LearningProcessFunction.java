import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import moa.core.TimingUtils;
import java.lang.*;
import com.yahoo.labs.samoa.instances.Instance;
import moa.classifiers.Classifier;
import moa.core.Example;
 
public class LearningProcessFunction extends ProcessFunction<Example<Instance>, Classifier> {
 
 	private static final long serialVersionUID = 1L;
 	private Class<? extends Classifier> clazz;
 	private Classifier classifier;
 	private int updateSize;
 	private long nbExampleSeen;
 	long startTime = System.nanoTime();  
 	
 	public LearningProcessFunction(Class<? extends Classifier> clazz, 		int updateSize) {
 			this.clazz = clazz;
 			this.updateSize = updateSize;
 	}
 	
 	@Override
 	public void open(Configuration parameters) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
 		classifier = clazz.newInstance();
 		classifier.prepareForUse();
 	}
 
 	@Override
 	public void processElement(Example<Instance> record, ProcessFunction<Example<Instance>, Classifier>.Context arg1,
                             Collector<Classifier> collector) throws Exception {
 
 			nbExampleSeen++;
 			classifier.trainOnInstance(record);
 			if (nbExampleSeen % updateSize == 0) {
 			collector.collect(classifier);
 			}
 			if (nbExampleSeen % 100_000 == 0) { //change the 100_000 to 250_000 and 500_000 for 80K, 200K, 400K training sets respectively
 			
 			long endTime = System.nanoTime();
 			long time =endTime-startTime;
 			double timesec = (double)time / 1_000_000_000.0;
 			
 			
 			System.err.println("         --> 							Instances: " + nbExampleSeen + " time: " +timesec +" 				seconds");
 			}
 
 		}
 
 }
