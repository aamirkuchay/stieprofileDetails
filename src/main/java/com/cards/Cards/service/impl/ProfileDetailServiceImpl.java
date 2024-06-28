package com.cards.Cards.service.impl;

import com.cards.Cards.dto.ProfileDetailsDTO;
import com.cards.Cards.dto.ProfileDetailsResponse;
import com.cards.Cards.entity.ProfileDetails;
import com.cards.Cards.respository.ProfileDetailsRepository;
import com.cards.Cards.service.ProfileDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ProfileDetailServiceImpl implements ProfileDetailService {

    @Autowired
    ProfileDetailsRepository profileDetailsRepository;

    private final String UPLOAD_DIR = "C:\\usr\\cards";


    @Override
    public ProfileDetails saveProfileDetails(ProfileDetailsDTO profileDetailsDTO) throws IOException {

        ProfileDetails profileDetails = new ProfileDetails();
        profileDetails.setName(profileDetailsDTO.getName());
        profileDetails.setProfession(profileDetailsDTO.getProfession());
        profileDetails.setProfile(profileDetailsDTO.getProfile());
        profileDetails.setMobileNumber(profileDetailsDTO.getMobileNumber());
        profileDetails.setAlternateNumber(profileDetailsDTO.getAlternateNumber());
        profileDetails.setEmail(profileDetailsDTO.getEmail());
        profileDetails.setAddress(profileDetailsDTO.getAddress());
        profileDetails.setCompanyName(profileDetailsDTO.getCompanyName());
        profileDetails.setLinkedInUrl(profileDetailsDTO.getLinkedInUrl());

        MultipartFile photoFile = profileDetailsDTO.getPhoto();
        if (photoFile != null && !photoFile.isEmpty()) {
            String photoFileName = System.currentTimeMillis() + "_" + photoFile.getOriginalFilename();
            Files.copy(photoFile.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + photoFileName));
            profileDetails.setPhoto(photoFileName);
        }

        return profileDetailsRepository.save(profileDetails);
        }

    @Override
    public ProfileDetailsResponse getProfileDetailsById(Long id) throws RuntimeException, IOException {
        ProfileDetails profileDetails = profileDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
        ProfileDetailsResponse response = new ProfileDetailsResponse(profileDetails);
        if (profileDetails.getPhoto() != null) {
            byte[] photoBytes = readPhotoFromFile(profileDetails.getPhoto());
            response.setPhoto(photoBytes);
        }
        return response;
    }

    private byte[] readPhotoFromFile(String photoFileName) throws IOException {
        return Files.readAllBytes(Paths.get("C:\\usr\\cards\\" + photoFileName));
    }

    @Override
    public ProfileDetails updateProfileDetails(Long id, ProfileDetailsDTO profileDetailsDTO) throws IOException {
        Optional<ProfileDetails> optionalProfileDetails = profileDetailsRepository.findById(id);
        if (optionalProfileDetails.isPresent()) {
            ProfileDetails profileDetails = optionalProfileDetails.get();
            profileDetails.setName(profileDetailsDTO.getName());
            profileDetails.setProfession(profileDetailsDTO.getProfession());
            profileDetails.setProfile(profileDetailsDTO.getProfile());
            profileDetails.setMobileNumber(profileDetailsDTO.getMobileNumber());
            profileDetails.setAlternateNumber(profileDetailsDTO.getAlternateNumber());
            profileDetails.setEmail(profileDetailsDTO.getEmail());
            profileDetails.setAddress(profileDetailsDTO.getAddress());
            profileDetails.setCompanyName(profileDetailsDTO.getCompanyName());
            profileDetails.setLinkedInUrl(profileDetailsDTO.getLinkedInUrl());

            MultipartFile photoFile = profileDetailsDTO.getPhoto();
            if (photoFile != null && !photoFile.isEmpty()) {
                String photoFileName = System.currentTimeMillis() + "_" + photoFile.getOriginalFilename();
                Files.copy(photoFile.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + photoFileName));
                profileDetails.setPhoto(photoFileName);
            }
            return profileDetailsRepository.save(profileDetails);
        } else {
            throw new RuntimeException("Profile not found");
        }
    }



    }


