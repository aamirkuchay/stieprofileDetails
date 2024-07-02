package com.cards.Cards.service;

import com.cards.Cards.dto.ProfileDetailsDTO;
import com.cards.Cards.dto.ProfileDetailsResponse;
import com.cards.Cards.entity.ProfileDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface ProfileDetailService {

    ProfileDetails saveProfileDetails(ProfileDetailsDTO profileDetailsDTO) throws IOException;

  

    ProfileDetails updateProfileDetails(Long id, ProfileDetailsDTO profileDetailsDTO) throws IOException;

    ProfileDetailsResponse getProfileDetailsById(Long id) throws IOException;

    Page<ProfileDetails> findAllByPage(Pageable pageable);
}
