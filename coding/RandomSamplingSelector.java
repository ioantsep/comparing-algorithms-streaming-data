import java.util.Collections;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import com.yahoo.labs.samoa.instances.Instance;
import moa.core.Example;

public class RandomSamplingSelector implements OutputSelector<Example<Instance>> {
 	
 	//names of the streams
 	public final static String TEST = "test";
 	public final static String TRAIN = "train";
 	
 	public final static Iterable<String> TEST_LIST = Collections.singletonList(TEST);
 	public final static Iterable<String> TRAIN_LIST = Collections.singletonList(TRAIN);
 	
 	private static final long serialVersionUID = 1L;
 	private double split;
 	private MersenneTwister rand = new MersenneTwister(11);
 	
 	public RandomSamplingSelector(double split) {
 		this.split = split;
 	}
 
 	@Override
 	public Iterable<String> select(Example<Instance> value) {
 		//random sampling
 		if (rand.nextFloat() < split) {
 			return TEST_LIST;
 		}
 		return TRAIN_LIST;
 	}
