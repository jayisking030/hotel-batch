package com.demo.batch.hotel.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.demo.batch.hotel.listener.HotelChunkListener;
import com.demo.batch.hotel.listener.HotelExecutionListener;
import com.demo.batch.hotel.model.Hotels;
import com.demo.batch.hotel.model.Rooms;
import com.demo.batch.hotel.processor.HotelItemProcessor;
import com.demo.batch.hotel.processor.RoomItemProcessor;
import com.demo.batch.hotel.writer.HotelReaderWriter;
import com.demo.batch.hotel.writer.RoomReaderWriter;

@Configuration
public class JobCofiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;
	
	@Autowired
	HotelReaderWriter hotelReaderWriter;
	
	@Autowired
	RoomReaderWriter roomReaderWriter;
	
	@Autowired
	HotelItemProcessor hotelItemProcessor;
	
	@Autowired
	RoomItemProcessor roomItemProcessor;
	
	@Autowired
	HotelExecutionListener hotelExecutionListener;
	
	@Autowired
	HotelChunkListener hotelChunkListener;


	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Hotels, Hotels>chunk(3)
				.reader(hotelReaderWriter.hotelItemReader())
				.processor(hotelItemProcessor)
				.writer(hotelReaderWriter.hotelItemWriter())
				.faultTolerant()
				.retryLimit(3)
				.retry(Exception.class)
				//.taskExecutor(taskExecutor())
				//.throttleLimit(5)
				.build();
	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3").<Rooms, Rooms>chunk(4)
				.reader(roomReaderWriter.roomItemReader())
				.processor(roomItemProcessor)
				.writer(roomReaderWriter.roomItemWriter())
				.faultTolerant()
				.retryLimit(3)
				.retry(Exception.class)
				//.taskExecutor(taskExecutor())
				//.throttleLimit(5)
				.build();
	}

	@Bean
	public Flow flow1() {
		return new FlowBuilder<SimpleFlow>("flow1").start(step1()).build();
	}

	@Bean
	public Flow flow2() {
		return new FlowBuilder<SimpleFlow>("flow2").start(step3()).build();
	}

	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor("hotelsThreads-");
		return taskExecutor;
	}

	@Bean
	public Flow parallelFlows() {
		return new FlowBuilder<SimpleFlow>("parallelFlows").split(taskExecutor()).add(flow1(), flow2()).build();
	}
	
	
	@Bean
	public Job processJob() {
	    return jobBuilderFactory.get("processJob")
	            .incrementer(new RunIdIncrementer())
	            .listener(hotelExecutionListener)
	            .start(parallelFlows())
	            .end()
	            .build();
	}
}
