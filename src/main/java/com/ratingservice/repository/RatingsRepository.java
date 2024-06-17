package com.ratingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ratingservice.entities.Ratings;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Integer> {
	List<Ratings> findByUserId(int userId);

	List<Ratings> findByHotelId(int hotelId);

	boolean existsByUserId(int userId);
	

	boolean existsByHotelId(int hotelId);
}
