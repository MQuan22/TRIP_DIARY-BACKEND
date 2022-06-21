package com.packandgo.tripdiary.controller;

import com.packandgo.tripdiary.exception.TripNotFoundException;
import com.packandgo.tripdiary.model.Comment;
import com.packandgo.tripdiary.model.Trip;

import com.packandgo.tripdiary.payload.request.trip.CommentRequest;
import com.packandgo.tripdiary.payload.request.trip.TripRequest;
import com.packandgo.tripdiary.payload.response.CommentResponse;
import com.packandgo.tripdiary.payload.response.MessageResponse;
import com.packandgo.tripdiary.payload.response.PagingResponse;
import com.packandgo.tripdiary.payload.response.TripResponse;
import com.packandgo.tripdiary.service.TripService;
import com.packandgo.tripdiary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {
    private final TripService tripService;
    private final UserService userService;

    @Autowired
    public TripController(TripService tripService, UserService userService) {
        this.tripService = tripService;
        this.userService = userService;
    }


    @GetMapping("/{id}")
    @ExceptionHandler(value = {TripNotFoundException.class})
    public ResponseEntity<?> getTrip(@PathVariable(name = "id", required = true) Long tripId) {
        Trip trip = tripService.get(tripId);
        TripResponse tripResponse = trip.toResponse();
        return ResponseEntity.ok(tripResponse);
    }


    @GetMapping("")
    public ResponseEntity<?> getAllTrips(@RequestParam(defaultValue = "1", required = false) int page,
                                         @RequestParam(defaultValue = "10", required = false) int size) {

        page = page <= 0 ? 1 : page;
        Page<Trip> trips = tripService.getTrips(page, size);
        PagingResponse<Trip> response = new PagingResponse<>(page, size, trips.getTotalPages(), trips.getContent());
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<?> insertTrip(@RequestBody TripRequest tripRequest) {
        Trip savedTrip = tripService.insertTrip(tripRequest);
        TripResponse tripResponse = savedTrip.toResponse();
        return ResponseEntity.ok(tripResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable(name = "id", required = true) Long tripId) {
        tripService.removeTrip(tripId);
        return ResponseEntity.ok(new MessageResponse("Trip was removed successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrip(@PathVariable(name = "id", required = true) Long tripId,
                                        @RequestBody TripRequest request) {

        Trip savedTrip = tripService.updateTrip(tripId, request);
        TripResponse tripResponse = savedTrip.toResponse();
        return ResponseEntity.ok(tripResponse);
    }

    @PostMapping("/like/{id}")

    public ResponseEntity<?> likeTrip(@PathVariable(name = "id", required = true) Long tripId) {
        tripService.likeTrip(tripId);
        return ResponseEntity.ok(new MessageResponse("OK"));
    }



    @PostMapping("/comment/{id}")
    public ResponseEntity<?> commentTrip(@PathVariable(name = "id", required = true) Long tripId,
                                         @RequestBody CommentRequest request){
        tripService.commentTrip(tripId, request);
        return ResponseEntity.ok(new MessageResponse("Comment successfully"));
    }
    @GetMapping("/api/trips/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable(name = "id", required = true) Long tripId){
        List<Comment> commentList = tripService.getCommentsByTripId(tripId);
        return ResponseEntity.ok(commentList);
    }
}