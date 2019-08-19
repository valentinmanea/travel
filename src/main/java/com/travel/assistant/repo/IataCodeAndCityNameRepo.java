package com.travel.assistant.repo;

import com.travel.assistant.entities.IataCodeAndCityName;

public interface IataCodeAndCityNameRepo  extends BaseRepository<IataCodeAndCityName>{

	IataCodeAndCityName findFirstByCityName(String cityName);

	IataCodeAndCityName findFirstByIataCode(String iataCodeForDestinationCity);

}
