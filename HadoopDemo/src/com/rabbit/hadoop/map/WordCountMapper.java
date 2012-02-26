package com.rabbit.hadoop.map;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class WordCountMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, IntWritable> {
	private static final IntWritable one = new IntWritable(1);
	private Text word = new Text();
	
	@Override
	public void map(LongWritable key1, Text  val1,
			OutputCollector<Text, IntWritable> output, Reporter rep)
			throws IOException {
		String str = val1.toString();
		StringTokenizer st = new StringTokenizer(str);
		
		while(st.hasMoreTokens()){
			word.set(st.nextToken());
			output.collect(word, one);
		}
	}

}
