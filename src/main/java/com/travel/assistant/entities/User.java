package com.travel.assistant.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true, 
value = {"hotelOffers"})
public class User extends BaseEntity implements UserDetails{
	
	@Column(name = "username")
	private String username; 
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
    private boolean enabled;
	
	@ManyToOne(fetch = FetchType.EAGER)
    private Role role;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	@JsonProperty(access = Access.WRITE_ONLY)
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<HotelOffer> hotelOffers;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE) 
	public List<FlightOffer> flightOffers;
	
	@OneToMany(mappedBy="rentalUser")
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE) 
	public List<CarOffer> carOffers;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<SearchHistory> histories;

	public void addHistory(SearchHistory history) { 
		if(this.histories == null) {
			this.histories = Arrays.asList(history);
		}else {
			this.histories.add(history);
		}
	}
	
	public void addHotelOffer(HotelOffer hotelOffer) {
		if(this.hotelOffers == null) {
			this.hotelOffers = Arrays.asList(hotelOffer);
		}else {
			this.hotelOffers.add(hotelOffer);
		}
	}
	@JsonProperty(access = Access.WRITE_ONLY)
	public List<HotelOffer> getAllHotelOffers(){
		return this.hotelOffers;
	}
	
	public void addFlightOffer(FlightOffer flightOffer) {
		if(this.flightOffers == null) {
			this.flightOffers = Arrays.asList(flightOffer);
		}else {
			this.flightOffers.add(flightOffer);
		}
	}
	public void buyCarOffer(CarOffer carOffer) {
		if(this.carOffers == null) {
			this.carOffers = Arrays.asList(carOffer);
		}else {
			this.carOffers.add(carOffer);
		}
	}
	@JsonProperty(access = Access.WRITE_ONLY)
	public List<SearchHistory> getAllHistories(){
		return this.histories;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public List<FlightOffer> getAllFlightOffers(){
		return this.flightOffers;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public List<CarOffer> getAllCarOffers(){
		return this.carOffers;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public boolean isDisabled() {
		return !enabled;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.getName()));
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
