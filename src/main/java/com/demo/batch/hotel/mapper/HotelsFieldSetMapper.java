package com.demo.batch.hotel.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.demo.batch.hotel.model.Hotels;

public class HotelsFieldSetMapper implements FieldSetMapper<Hotels> {

	@Override
	public Hotels mapFieldSet(FieldSet fieldSet) throws BindException {
		return new Hotels(fieldSet.readInt("id"), fieldSet.readString("hotelName"), fieldSet.readString("description"),
				fieldSet.readString("cityCode"));
	}

}
