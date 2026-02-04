package com.Edulink.EdulinkServer.dto.user;

import com.Edulink.EdulinkServer.enums.TeachingLevel;
import org.springframework.stereotype.Component;

@Component
public class UserResponseDTO {
    private Long userId;

    private boolean student;
    private boolean teacher;
    private String email;
    private String role;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String[] teachingSubjects;
    private TeachingLevel teachingLevel;
    private String shortBio;
    private int yearsOfExperience;
    private String certificateUrl;
    private String certificateImageType;
    private String certificateImageName;


    private String bankAccount;
    private String bankName;
    private String bankCode;

    private String subAccountCode;

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getSubAccountCode() {
        return subAccountCode;
    }

    public void setSubAccountCode(String subAccountCode) {
        this.subAccountCode = subAccountCode;
    }

    private int sctaPoints = 0;

    private int noOfSessions = 0;

    public int getNoOfSessions() {
        return noOfSessions;
    }

    public void setNoOfSessions(int noOfSessions) {
        this.noOfSessions = noOfSessions;
    }

    public int getSctaPoints() {
        return sctaPoints;
    }

    public void setSctaPoints(int sctaPoints) {
        this.sctaPoints = sctaPoints;
    }


    public String getCertificateUrl() {
        return certificateUrl;
    }

    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }

    public String getCertificateImageType() {
        return certificateImageType;
    }

    public void setCertificateImageType(String certificateImageType) {
        this.certificateImageType = certificateImageType;
    }

    public String getCertificateImageName() {
        return certificateImageName;
    }

    public void setCertificateImageName(String certificateImageName) {
        this.certificateImageName = certificateImageName;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public boolean isTeacher() {
        return teacher;
    }

    public void setTeacher(boolean teacher) {
        this.teacher = teacher;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String[] getTeachingSubjects() {
        return teachingSubjects;
    }

    public void setTeachingSubjects(String[] teachingSubjects) {
        this.teachingSubjects = teachingSubjects;
    }

    public TeachingLevel getTeachingLevel() {
        return teachingLevel;
    }

    public void setTeachingLevel(TeachingLevel teachingLevel) {
        this.teachingLevel = teachingLevel;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }


}
