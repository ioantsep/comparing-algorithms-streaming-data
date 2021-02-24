import org.apache.flink.streaming.api.functions.co.CoFlatMapFunction;
import org.apache.flink.util.Collector;
import com.yahoo.labs.samoa.instances.Instance;
import moa.classifiers.Classifier;
import moa.classifiers.functions.NoChange;
import moa.core.Example;

public class ClassifyAndUpdateClassifierFunction implements CoFlatMapFunction<Example<Instance>, Classifier, Boolean> {
 	
 		private static final long serialVersionUID = 1L;

		//default 	classifier - return 0 if didn't learn
 		private Classifier classifier = new NoChange(); 
 
 		@Override
 		public void flatMap1(Example<Instance> value, Collector<Boolean> out) throws Exception {
				out.collect(classifier.correctlyClassifies(value.getData()));
 		}
 
 		@Override
 		public void flatMap2(Classifier classifier, Collector<Boolean> out) throws Exception {
 			//update the classifier when a new version is sent
 				this.classifier = classifier;
 		
 		}
 }
