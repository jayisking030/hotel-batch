package com.demo.batch.hotel.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.demo.batch.hotel.model.Rooms;

@Service
public class RoomItemProcessor implements ItemProcessor<Rooms, Rooms> {

	private static final Logger logger = LoggerFactory.getLogger(RoomItemProcessor.class);

	@Override
	public Rooms process(Rooms rooms) throws Exception {

		logger.info("RoomItemProcessor id  {} - Description  {} - Hotel Id  {}", rooms.getRoomId(),
				rooms.getDescription(), rooms.getHotelId());

		return rooms;
	}
}