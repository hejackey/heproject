package com.rabbit.hadoop.reduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 * reduce函数实现类
 * @author hr
 *
 */
public class TemperatureReduce extends MapReduceBase implements
		Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	public void reduce(Text key, Iterator<IntWritable> value,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		int maxValue = Integer.MAX_VALUE;
		
		while(value.hasNext()){
			maxValue = Math.max(maxValue, value.next().get());
		}
		
		output.collect(key, new IntWritable(maxValue));
	}
	
}
