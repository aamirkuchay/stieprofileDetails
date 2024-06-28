package com.cards.Cards.controller;

import com.cards.Cards.dto.ProfileDetailsDTO;
import com.cards.Cards.dto.ProfileDetailsResponse;
import com.cards.Cards.entity.ProfileDetails;
import com.cards.Cards.service.ProfileDetailService;
import com.cards.Cards.utility.QRCodeGenerator;
import com.google.zxing.WriterException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/profile")
public class ProfileDetailsController {

    @Autowired
    private ProfileDetailService profileDetailService;

    private  final String QRCODE_URL = "http://localhost:8089/profile/";

    @PostMapping("/save")
    public ResponseEntity<ProfileDetails> saveProfileDetails(@Valid @ModelAttribute ProfileDetailsDTO profileDetailsDTO,
            @RequestParam("photo") MultipartFile photo) throws IOException {
        profileDetailsDTO.setPhoto(photo);
        ProfileDetails savedProfile = profileDetailService.saveProfileDetails(profileDetailsDTO);
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDetailsResponse> getProfileDetailsById(@PathVariable Long id) throws IOException {
        ProfileDetailsResponse response = profileDetailService.getProfileDetailsById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfileDetails> updateProfileDetails(
            @PathVariable Long id,
            @Valid @ModelAttribute ProfileDetailsDTO profileDetailsDTO,
            @RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException {

        if (photo != null) {
            profileDetailsDTO.setPhoto(photo);
        }
        ProfileDetails updatedProfile = profileDetailService.updateProfileDetails(id, profileDetailsDTO);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }


    @GetMapping("/qrcode/{id}")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable Long id) throws WriterException, IOException {
        String url = QRCODE_URL + id;
        byte[] qrCodeImage = QRCodeGenerator.generateQRCodeImage(url, 350, 350);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return ResponseEntity.ok().headers(headers).body(qrCodeImage);
    }

}

