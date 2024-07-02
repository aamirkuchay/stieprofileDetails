package com.cards.Cards.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProfileDetailsDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String profession;
    private String profile;
    private String mobileNumber;
    private String alternateNumber;
    private String country;
    private String email;
    private String pincode;
    private String state;
    private String street;
    private String companyName;
    private String linkedInUrl;
    private MultipartFile photo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAlternateNumber() {
        return alternateNumber;
    }

    public void setAlternateNumber(String alternateNumber) {
        this.alternateNumber = alternateNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLinkedInUrl() {
        return linkedInUrl;
    }

    public void setLinkedInUrl(String linkedInUrl) {
        this.linkedInUrl = linkedInUrl;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
