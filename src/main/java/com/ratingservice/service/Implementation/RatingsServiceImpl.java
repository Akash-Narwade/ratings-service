package com.ratingservice.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratingservice.entities.Ratings;
import com.ratingservice.exception.HotelNotFoundException;
import com.ratingservice.exception.ResourceNotFoundException;
import com.ratingservice.exception.UserNotFoundException;
import com.ratingservice.repository.RatingsRepository;
import com.ratingservice.service.RatingsService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RatingsServiceImpl implements RatingsService {

	@Autowired
	private final RatingsRepository ratingsRepository;

	@Override
	public Ratings createRatings(Ratings ratings) {
		Ratings saveUser = this.ratingsRepository.save(ratings);
		return saveUser;
	}

	@Override
	public Ratings updateRatings(Ratings ratings, int rating_id) {
		Ratings updateRatings = this.ratingsRepository.findById(rating_id)
				.orElseThrow(() -> new ResourceNotFoundException("Rating with" + rating_id + "not found on server"));
		updateRatings.setRating(ratings.getRating());
		updateRatings.setFeedback(ratings.getFeedback());
		updateRatings.setUserId(ratings.getUserId());
		updateRatings.setHotelId(ratings.getHotelId());
		return this.ratingsRepository.save(updateRatings);
	}

	@Override
	public List<Ratings> getAllRatings() {
		return this.ratingsRepository.findAll();
	}

	@Override
	public List<Ratings> getRatingsByUser(int userId) {
		if (ratingsRepository.existsByUserId(userId)) {
			return this.ratingsRepository.findByUserId(userId);
		} else {

		}
		throw new UserNotFoundException("User with ID " + userId + " not found");
	}

	@Override
	public List<Ratings> getRatingsByHotel(int hoteId) {
		if (ratingsRepository.existsByHotelId(hoteId)) {
			return this.ratingsRepository.findByHotelId(hoteId);
		} else {

		}
		throw new HotelNotFoundException("Hotel with ID " + hoteId + " not found");
	}

	@Override
	public void deleteHotel(int rating_id) {
		Ratings deleteHotel = this.ratingsRepository.findById(rating_id)
				.orElseThrow(() -> new ResourceNotFoundException("Rating with " + rating_id + " not found on server"));
		;
		this.ratingsRepository.delete(deleteHotel);
	}

}
