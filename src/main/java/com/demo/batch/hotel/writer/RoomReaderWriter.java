package com.demo.batch.hotel.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.demo.batch.hotel.mapper.RoomsFieldSetMapper;
import com.demo.batch.hotel.model.Rooms;

@Component
public class RoomReaderWriter {
	
	@Autowired
	public DataSource dataSource;
	

	@Bean
	public FlatFileItemReader<Rooms> roomItemReader() {
		FlatFileItemReader<Rooms> reader = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource("/data/room.csv"));

		DefaultLineMapper<Rooms> roomLineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(new String[] { "roomId", "description", "hotelId" });

		roomLineMapper.setLineTokenizer(tokenizer);
		roomLineMapper.setFieldSetMapper(new RoomsFieldSetMapper());
		roomLineMapper.afterPropertiesSet();
		reader.setLineMapper(roomLineMapper);
		return reader;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public JdbcBatchItemWriter<Rooms> roomItemWriter() {
		JdbcBatchItemWriter<Rooms> itemWriter = new JdbcBatchItemWriter<>();

		itemWriter.setDataSource(this.dataSource);
		itemWriter.setSql("INSERT INTO ROOMS VALUES (:roomId, :description, :hotelId)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
		itemWriter.afterPropertiesSet();

		return itemWriter;
	}
}
