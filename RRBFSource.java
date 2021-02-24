import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import com.yahoo.labs.samoa.instances.Instance;
import moa.core.Example;
import moa.streams.generators.RandomRBFGenerator;
 
public class RRBFSource extends RichParallelSourceFunction<Example<Instance>> {
 	
 		private static final long serialVersionUID = 1L;
 		private boolean isRunning = false;
 		private RandomRBFGenerator rrbf = new RandomRBFGenerator();
 
 		@Override
 		public void open(Configuration parameters) throws Exception {
 			rrbf.prepareForUse();
 		}
 		
 		@Override
 		public void cancel() {
 			isRunning = false;
 		}
 
 		@Override
 		public void run(SourceContext<Example<Instance>> sc) throws Exception {
 			isRunning = true;
 			while (isRunning) {
 				sc.collect(rrbf.nextInstance());
 			}
 		}
}
