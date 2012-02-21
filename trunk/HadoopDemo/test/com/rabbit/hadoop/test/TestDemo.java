package com.rabbit.hadoop.test;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import com.rabbit.hadoop.map.TemperatureMap;
import com.rabbit.hadoop.reduce.TemperatureReduce;

public class TestDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length!=2){
			System.err.println("usage:tempareture <input path> <output path>");
			System.exit(1);
		}
		
		JobConf conf = new JobConf(TestDemo.class);
		conf.setJobName("max temperature");
		
		FileInputFormat.addInputPath(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		conf.setMapperClass(TemperatureMap.class);
		conf.setReducerClass(TemperatureReduce.class);
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		try {
			JobClient.runJob(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
