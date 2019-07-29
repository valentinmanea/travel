package com.travel.assistant.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Hotel extends BaseEntity{
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy="hotel")
	private List<HousingReservation> reservations;

	@JoinColumn(name="city")
	private String city;
	
	@OneToMany(mappedBy = "hotel",cascade= CascadeType.ALL)
	private List<Room> rooms;
	
	@Column(name = "img_src")
	private String imgSrc;
	
	public List<LocalDate> retrieveAvailableDates(){
		List<LocalDate> reservedDates = reservations.stream()
				.map(this::retrieveReservedDates)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		List<LocalDate> availableDates = retrieveAllDatesFromCurrentMonth();
		availableDates.removeAll(reservedDates);
		return availableDates;
	}
	
	public List<LocalDate> retrieveAllReservedDates(){
		return reservations.stream()
				.map(this::retrieveReservedDates)
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}
	public List<LocalDate> retrieveReservedDates(HousingReservation reservation) {
		LocalDate startDate = reservation.getStartDate();
		LocalDate endDate = reservation.getEndDate();
		List<LocalDate> dates = Stream.iterate(startDate, date -> date.plusDays(1))
			    .limit(ChronoUnit.DAYS.between(startDate, endDate.plusDays(1L)))
			    .collect(Collectors.toList());
		return dates;
	}
	
	public List<LocalDate> retrieveAllDatesFromCurrentMonth(){
		LocalDate date = LocalDate.now().withDayOfMonth(1);
	    LocalDate end = date.plusMonths(1);
	    List<LocalDate> dates = new ArrayList<>();
	    while(date.isBefore(end)) {
	        dates.add(date);
	        date = date.plusDays(1);
	    }
	    return dates;
	}
	
	public void addReservation(HousingReservation r) {
		reservations.add(r);
	}
	
	public void removeReservation(HousingReservation r) {
		reservations.remove(r);
	}

	public void addRooms(List<Room> rooms) {
		if(this.rooms != null) {
			this.rooms.addAll(rooms);
		}else {
			this.rooms = rooms;
		}
	}

	@Override
	public String toString() {
		return "Hotel [name=" + name + ", reservations=" + reservations + ", city=" + city + ", rooms=" + rooms
				+ ", imgSrc=" + imgSrc + ", toString()=" + super.toString() + "]";
	}

	
}
