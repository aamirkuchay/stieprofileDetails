package com.cards.Cards.controller;

import com.cards.Cards.dto.ProfileDetailsDTO;
import com.cards.Cards.dto.ProfileDetailsResponse;
import com.cards.Cards.entity.ProfileDetails;
import com.cards.Cards.exception.ResourceNotFoundException;
import com.cards.Cards.service.ProfileDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ProfileControllerTest {

    @Mock
    private ProfileDetailService profileDetailService;

    @InjectMocks
    private ProfileDetailsController profileController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testSaveProfileDetails() throws IOException {
        ProfileDetailsDTO profileDetailsDTO = new ProfileDetailsDTO();
        profileDetailsDTO.setFirstName("John");
        profileDetailsDTO.setLastName("Doe");
        profileDetailsDTO.setProfession("Developer");
        profileDetailsDTO.setCountry("india");
        profileDetailsDTO.setEmail("aamir@gmail.com");
        profileDetailsDTO.setMobileNumber("8888888888");
        profileDetailsDTO.setProfession("java developer");
        profileDetailsDTO.setPincode("192122");


        MockMultipartFile photo = new MockMultipartFile(
                "photo",
                "photo.jpg",
                "image/jpeg",
                "some image content".getBytes()
        );

        profileDetailsDTO.setPhoto(photo);

        ProfileDetails savedProfileDetails = new ProfileDetails();
        savedProfileDetails.setFirstName("John");
        savedProfileDetails.setLastName("Doe");
        savedProfileDetails.setProfession("Developer");
        savedProfileDetails.setCountry("india");
        savedProfileDetails.setEmail("aamir@gmail.com");
        savedProfileDetails.setMobileNumber("8888888888");
        savedProfileDetails.setProfession("java developer");
        savedProfileDetails.setPincode("192122");
        // set other fields
        savedProfileDetails.setPhoto("photo.jpg");

        when(profileDetailService.saveProfileDetails(any(ProfileDetailsDTO.class))).thenReturn(savedProfileDetails);

        ResponseEntity<ProfileDetails> responseEntity = profileController.saveProfileDetails(profileDetailsDTO, photo);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedProfileDetails, responseEntity.getBody());
    }



    @Test
    public void testGetProfileDetailsById() throws IOException {
        Long id = 1L;

        ProfileDetails profileDetails = new ProfileDetails();
        profileDetails.setId(id);
        profileDetails.setFirstName("John");
        profileDetails.setLastName("Doe");
        profileDetails.setProfession("Developer");
        // set other fields
        profileDetails.setPhoto("photo.jpg");

        ProfileDetailsResponse profileDetailsResponse = new ProfileDetailsResponse(profileDetails);
        profileDetailsResponse.setPhoto(new byte[]{1, 2, 3});

        when(profileDetailService.getProfileDetailsById(anyLong())).thenReturn(profileDetailsResponse);

        ResponseEntity<ProfileDetailsResponse> responseEntity = profileController.getProfileDetailsById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(profileDetailsResponse, responseEntity.getBody());
    }

    @Test
    public void testGetProfileDetailsByIdNotFound() throws IOException {
        Long id = 0L;

        when(profileDetailService.getProfileDetailsById(anyLong())).thenThrow(new ResourceNotFoundException("Profile not found with id: " + id));

        assertThrows(ResourceNotFoundException.class, () -> {
            profileController.getProfileDetailsById(id);
        });


    }



    @Test
    public void testFindAllByPage() {
        int page = 1;
        Pageable pageable = PageRequest.of(page - 1, 10);

        ProfileDetails profile1 = new ProfileDetails();
        profile1.setFirstName("John");
        profile1.setLastName("Doe");
        profile1.setProfession("Developer");


        ProfileDetails profile2 = new ProfileDetails();
        profile2.setFirstName("Jane");
        profile2.setLastName("Doe");
        profile2.setProfession("Designer");


        List<ProfileDetails> profiles = Arrays.asList(profile1, profile2);
        Page<ProfileDetails> profilePage = new PageImpl<>(profiles, pageable, profiles.size());

        when(profileDetailService.findAllByPage(any(Pageable.class))).thenReturn(profilePage);

        ResponseEntity<Page<ProfileDetails>> responseEntity = profileController.findAllByPage(page);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(profilePage, responseEntity.getBody());
    }


}
