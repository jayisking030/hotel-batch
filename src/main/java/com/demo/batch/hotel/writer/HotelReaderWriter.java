package com.demo.batch.hotel.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.demo.batch.hotel.mapper.HotelsFieldSetMapper;
import com.demo.batch.hotel.model.Hotels;

@Component
public class HotelReaderWriter {
	
	@Autowired
	public DataSource dataSource;
	

	public FlatFileItemReader<Hotels> hotelItemReader() {
		FlatFileItemReader<Hotels> reader = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource("/data/hotels.csv"));

		DefaultLineMapper<Hotels> hotelLineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames(new String[] { "id", "hotelName", "description", "cityCode" });

		hotelLineMapper.setLineTokenizer(tokenizer);
		hotelLineMapper.setFieldSetMapper(new HotelsFieldSetMapper());
		hotelLineMapper.afterPropertiesSet();
		reader.setLineMapper(hotelLineMapper);
		return reader;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JdbcBatchItemWriter<Hotels> hotelItemWriter() {
		JdbcBatchItemWriter<Hotels> itemWriter = new JdbcBatchItemWriter<>();

		itemWriter.setDataSource(this.dataSource);
		itemWriter.setSql("INSERT INTO HOTELS VALUES (:id, :hotelName, :description, :cityCode)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
		itemWriter.afterPropertiesSet();

		return itemWriter;
	}
}
