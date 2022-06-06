package com.hackerrank.weather.service;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.weather.model.Location;
import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.model.WeatherHiLo;

@Service
public class WeatherService {

	@Autowired
	private Database database;

	public Weather create(Weather weather) {
		validate(weather);

		if (getRecords().containsKey(weather.getId())) {
			throw new ServiceException("weather already exists");
		}

		getRecords().put(weather.getId(), weather);
		return weather;
	}

	public void delete(Date start, Date end, Float lat, Float lon) {
		List<Long> ids = getRecords().values().stream()
		.filter(w -> 
			(start.equals(w.getDateRecorded()) || start.before(w.getDateRecorded())) && 
			(end.equals(w.getDateRecorded()) || end.after(w.getDateRecorded())) && 
			lat.equals(w.getLocation().getLatitude()) && lon.equals(w.getLocation().getLongitude())
		)
		.map(Weather::getId)
		.collect(toList());

		ids.stream().forEach(id -> {
			getRecords().remove(id);
		});
	}

	public List<Weather> findByLocation(Float lat, Float lon) {
		return getRecords().values().stream()
			.filter(w -> lat.equals(w.getLocation().getLatitude()) && lon.equals(w.getLocation().getLongitude()))
			.collect(toList());
	}

	public List<WeatherHiLo> findByDate(Date start, Date end) {
		List<Weather> records = getRecords().values().stream()
		.filter(w -> 
			(start.equals(w.getDateRecorded()) || start.before(w.getDateRecorded())) && 
			(end.equals(w.getDateRecorded()) || end.after(w.getDateRecorded()))
		)
		.collect(toList());

		Map<Location, Map<String, Float>> rawResults = records.stream()
		.collect(groupingBy(Weather::getLocation, 
			Collector.of(
				HashMap<String, Float>::new, 
				(m, w) -> {
					Arrays.stream(w.getTemperature()).forEach((Float t) -> {
						if (!m.containsKey("highest") || t.compareTo(m.get("highest")) > 0) {
							m.put("highest", t);
						}
						if (!m.containsKey("lowest") || t.compareTo(m.get("lowest")) < 0) {
							m.put("lowest", t);
						}
					});
				}, 
				(m1, m2) -> {
					m1.putAll(m2);
					return m1;
				}
			)
		));

		List<WeatherHiLo> objResults = rawResults.entrySet().stream()
		.map(e -> {
			Location loc = e.getKey();
			Map<String, Float> tmp = e.getValue();
			return new WeatherHiLo(
				loc.getCityName(), 
				loc.getStateName(), 
				loc.getLatitude(), 
				loc.getLongitude(), 
				tmp.get("highest"), 
				tmp.get("lowest"));
		})
		.collect(toList());

		objResults.sort(comparing(WeatherHiLo::getCityName));

		return objResults;
	}

	public List<Weather> findAll() {
		return new ArrayList<Weather>(getRecords().values());
	}

	/**
	 * Convenience method.
	 */
	private Map<Long, Weather> getRecords() {
		return database.getRecords();
	}

	/**
	 * Checks whether a weather object is valid.
	 */
	private void validate(Weather weather) {
		if (weather == null) {
			throw new ServiceException("weather is null");
		}

		if (weather.getDateRecorded() == null) {
			throw new ServiceException("weather.dateRecorded is empty");
		}

		if (weather.getLocation() == null) {
			throw new ServiceException("weather.location is empty");
		}

		if (weather.getLocation().getLatitude() == null) {
			throw new ServiceException("weather.location.latitude is empty");
		}

		if (weather.getLocation().getLongitude() == null) {
			throw new ServiceException("weather.location.longitude is empty");
		}
	}
}
