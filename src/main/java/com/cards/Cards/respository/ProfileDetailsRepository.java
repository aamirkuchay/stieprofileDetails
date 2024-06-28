package com.cards.Cards.respository;

import com.cards.Cards.entity.ProfileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDetailsRepository extends JpaRepository<ProfileDetails, Long> {
}
