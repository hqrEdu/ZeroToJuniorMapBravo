package com.map.service;

import com.map.dto.UserDto;
import com.map.model.User;
import com.map.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GeocodingService geocodingService;

    public User createUser(UserDto userDto) {
        User user = User.builder()
                .nickname(userDto.getNickname())
                .zipCode(userDto.getZipCode())
                .city(userDto.getCity())
                .country(userDto.getCountry())
                .latitude(geocodingService.getLatitude(getAddressForUser(userDto)))
                .longitude(geocodingService.getLongitude(getAddressForUser(userDto)))
                .build();
        return userRepository.addUser(user);
    }

    private String getAddressForUser(UserDto userDto) {
        return userDto.getCountry() + userDto.getCity() + userDto.getZipCode();
    }

    public List<User> getUsers() {
        return userRepository.getAllUsers();
    }
}