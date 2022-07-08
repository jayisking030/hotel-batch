package com.demo.batch.hotel.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import com.demo.batch.hotel.model.Hotels;

@Service
public class HotelItemProcessor implements ItemProcessor<Hotels, Hotels> {

	private static final Logger logger = LoggerFactory.getLogger(HotelItemProcessor.class);

	@Override
	public Hotels process(Hotels hotels) throws Exception {

		logger.info("HotelItemProcessor Name  {} - Description  {} - CityCode  {}", 
				hotels.getHotelName(), hotels.getDescription(), hotels.getCityCode());

		return hotels;
	}
}