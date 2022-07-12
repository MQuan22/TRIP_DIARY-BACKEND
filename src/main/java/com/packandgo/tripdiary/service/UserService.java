package com.packandgo.tripdiary.service;

import com.packandgo.tripdiary.model.Trip;
import com.packandgo.tripdiary.model.User;
import com.packandgo.tripdiary.model.UserInfo;
import com.packandgo.tripdiary.payload.request.auth.NewPasswordRequest;
import com.packandgo.tripdiary.payload.request.auth.RegisterRequest;
import com.packandgo.tripdiary.payload.request.user.InfoUpdateRequest;
import com.packandgo.tripdiary.payload.response.AdminResponse;
import com.packandgo.tripdiary.payload.response.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    public User findUserByEmail(String email);
    public User findUserByUsername(String username);
    public User findUserByUsernameOrEmail(String usernameOrEmail);
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
    public String createPasswordResetTokenForUser(User user);
    public void register(RegisterRequest request, String backendUrl) throws Exception;
    public boolean verify(String verifyToken);
    public void changePassword(User user, String newPassword);
    public void removeUser(String username);
    public void saveUserInfo(UserInfo info);
    public void resetPassword(NewPasswordRequest request);
    public UserInfo getInfo(User user);

    public void updateUserInfo(User user, InfoUpdateRequest infoUpdateRequest);
    public List<Trip> getTripsForUser(User user, String me);
    public Page<UserResponse> getUsersAndAllTrips(int page, int size);
    public User blockUsers(String username);
    public User unblockUsers(String username);
    public List<UserResponse> search(String keyword);
    public AdminResponse getUserInfo(String username);
    public void grantAdmin(String username);
    public void revokeAdmin(String username);
}
