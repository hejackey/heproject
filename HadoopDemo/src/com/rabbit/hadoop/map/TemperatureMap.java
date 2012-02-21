package com.rabbit.hadoop.map;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 * map函数实现类
 * @author hr
 *
 */
public class TemperatureMap extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, IntWritable> {
	private static final int MISSING= 9999;
	
	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output,
			Reporter reporter) throws IOException {
		String line = value.toString();
		String year = line.substring(15,19);
		int temp;
		
		if(line.charAt(87) == '+')
			temp = Integer.parseInt(line.substring(88,92));
		else
			temp = Integer.parseInt(line.substring(87,92));
		
		String quality = line.substring(92,93);
		
		if(temp != MISSING && quality.matches("[01459]"))
			output.collect(new Text(year), new IntWritable(temp));
	}

}
