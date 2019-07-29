package com.travel.assistant.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.travel.assistant.facade.dtos.DateDto;
@Service
public class DateService {

	public List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
		return Stream.iterate(startDate, d -> d.plusDays(1))
				.limit(ChronoUnit.DAYS.between(startDate, endDate) + 1).collect(Collectors.toList());
	}
	
	public LocalDate toLocalDate(DateDto dto){
		return LocalDate.parse(dto.year + "-" + dto.month + "-" + dto.day, DateTimeFormatter.ofPattern("yyyy-M-d"));
	}
	
	public DateDto toDateDto(LocalDate localDate){
		DateDto dto = new DateDto();
		dto.day = "" +localDate.getDayOfMonth();
		dto.month = "" +localDate.getMonthValue();
		dto.year = "" +localDate.getYear();
		
		return dto;
	}
}