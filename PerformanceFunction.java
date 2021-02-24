import org.apache.flink.api.common.accumulators.AverageAccumulator;
import org.apache.flink.api.common.functions.AggregateFunction;
 
public class PerformanceFunction implements AggregateFunction<Boolean, AverageAccumulator, Double> {
 		private static final long serialVersionUID = 1L;
 
 		@Override
 		public AverageAccumulator createAccumulator() {
 		  return new AverageAccumulator();
 		}
 
 		@Override
 		public AverageAccumulator add(Boolean value, AverageAccumulator accumulator) {
 		  accumulator.add(value ? 1 : 0);
 		  return accumulator;
 		}
 
 		@Override
 		public Double getResult(AverageAccumulator accumulator) {
 		  return accumulator.getLocalValue();
 		}
 
 		@Override
 		public AverageAccumulator merge(AverageAccumulator a, AverageAccumulator b) {
 		  a.merge(b);
 		  return a;
 		}

 }
