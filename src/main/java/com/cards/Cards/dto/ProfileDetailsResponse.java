package com.cards.Cards.dto;

import com.cards.Cards.entity.ProfileDetails;

public class ProfileDetailsResponse {

    private Long id;
    private String name;
    private String profession;
    private String profile;
    private String mobileNumber;
    private String alternateNumber;
    private String email;
    private String address;
    private String companyName;
    private String linkedInUrl;
    private byte[] photo;
//    private byte[] qrCode;

    public ProfileDetailsResponse(ProfileDetails profileDetails) {
        this.id = profileDetails.getId();
        this.name = profileDetails.getName();
        this.profession = profileDetails.getProfession();
        this.profile = profileDetails.getProfile();
        this.mobileNumber = profileDetails.getMobileNumber();
        this.alternateNumber = profileDetails.getAlternateNumber();
        this.email = profileDetails.getEmail();
        this.address = profileDetails.getAddress();
        this.companyName = profileDetails.getCompanyName();
        this.linkedInUrl = profileDetails.getLinkedInUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

//    public byte[] getQrCode() {
//        return qrCode;
//    }
//
//    public void setQrCode(byte[] qrCode) {
//        this.qrCode = qrCode;
//    }
}
