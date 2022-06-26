package com.packandgo.tripdiary.service;

import com.packandgo.tripdiary.model.Comment;
import com.packandgo.tripdiary.model.Trip;
import com.packandgo.tripdiary.model.User;
import com.packandgo.tripdiary.payload.request.trip.CommentRequest;
import com.packandgo.tripdiary.payload.request.trip.TripRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TripService {
    public Trip insertTrip(TripRequest request);
    public Page<Trip> getTrips(int page, int size);
    public void removeTrip(Long id);
    public Trip updateTrip(Long tripId, TripRequest request);
    public Trip get(Long id);
    public void likeTrip(Long tripId);
    public boolean existedTrip(Long tripId);
    public boolean existedLike(Long tripId);
    public List<Trip> getNotifiedTripsForDay();
    public void commentTrip(Long tripId, String content);
    public List<Comment> getCommentsByTripId(Long tripId);

    public void inviteToJoinTrip(Long tripId, String username);

    public void removeTripMate(Long tripId, String username);
    public void deleteComment(Long commentId);
    public void editComment(CommentRequest request);
    public void replyComment(Long tripId, CommentRequest request);
}
