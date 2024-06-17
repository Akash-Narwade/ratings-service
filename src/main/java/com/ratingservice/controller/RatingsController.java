package com.ratingservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingservice.entities.Ratings;
import com.ratingservice.playloads.APIResponse;
import com.ratingservice.service.RatingsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingsController {
	 private final RatingsService ratingsService;

	    @PostMapping("/createRatings")
	    public ResponseEntity<Ratings> createRatings(@Valid @RequestBody Ratings ratings) {
	        Ratings createRatings = this.ratingsService.createRatings(ratings);
	        return new ResponseEntity<>(createRatings, HttpStatus.CREATED);
	    }

	    @PutMapping("/updateRatings/{rating_id}")
	    public ResponseEntity<Ratings> updateRatings(@Valid @RequestBody Ratings ratings,@PathVariable int rating_id) {
	        Ratings updteRatings = this.ratingsService.updateRatings(ratings, rating_id);
	        return ResponseEntity.ok(updteRatings);
	    }

	    @GetMapping("/ratingsList")
	    public ResponseEntity<List<Ratings>> getRatings() {
	        List<Ratings> allRatings = this.ratingsService.getAllRatings();
	        return ResponseEntity.ok(allRatings);
	    }

	    @GetMapping("/ratingsByUser/{userId}")
	    public ResponseEntity<List<Ratings>> getRatingsByUser(@PathVariable int userId) {
	        List<Ratings> ratingsByUser = this.ratingsService.getRatingsByUser(userId);
	        return ResponseEntity.ok(ratingsByUser);
	    }

	    @GetMapping("/ratingsByHotel/{hotelId}")
	    public ResponseEntity<List<Ratings>> getRatingsByHotel(@PathVariable int hotelId) {
	        List<Ratings> ratingsByHotel = this.ratingsService.getRatingsByHotel(hotelId);
	        return ResponseEntity.ok(ratingsByHotel);
	    }

	    @DeleteMapping("/deleteRatings/{rating_id}")
	    public ResponseEntity<APIResponse> deleteRating(@PathVariable int rating_id){
	        this.ratingsService.deleteHotel(rating_id);
	        return new ResponseEntity<>(new APIResponse("Rating deleted successfully!!",true),HttpStatus.OK);
	    }

}
