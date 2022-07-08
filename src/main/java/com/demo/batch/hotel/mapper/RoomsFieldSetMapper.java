package com.demo.batch.hotel.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.demo.batch.hotel.model.Rooms;

public class RoomsFieldSetMapper implements FieldSetMapper<Rooms> {

	@Override
	public Rooms mapFieldSet(FieldSet fieldSet) throws BindException {
		// TODO Auto-generated method stub
		return new Rooms(fieldSet.readInt("roomId"), fieldSet.readString("description"), fieldSet.readInt("hotelId"));
	}

}
