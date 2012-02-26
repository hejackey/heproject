package com.rabbit.hadoop.reduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class WordCountReduce extends MapReduceBase implements
		Reducer< Text, IntWritable,Text, IntWritable> {

	@Override
	public void reduce(Text key, Iterator<IntWritable> val,
			OutputCollector<Text, IntWritable> output, Reporter rep)
			throws IOException {
		int sum = 0 ;
		
		while(val.hasNext())
			sum += val.next().get();
		
		output.collect(key, new IntWritable(sum));
	}

}
