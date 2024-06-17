package com.ratingservice.service;

import java.util.List;

import com.ratingservice.entities.Ratings;

public interface RatingsService {

	Ratings createRatings(Ratings ratings);

	Ratings updateRatings(Ratings ratings, int rating_id);

	List<Ratings> getAllRatings();

	List<Ratings> getRatingsByUser(int userId);

	List<Ratings> getRatingsByHotel(int hoteId);

	void deleteHotel(int rating_id);

}
