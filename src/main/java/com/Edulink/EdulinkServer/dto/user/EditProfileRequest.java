package com.Edulink.EdulinkServer.dto.user;


import com.Edulink.EdulinkServer.enums.TeachingLevel;
import org.springframework.stereotype.Component;

@Component
public class EditProfileRequest {
    private String firstName;
    private String lastName;
    private String[] teachingSubjects;
    private TeachingLevel teachingLevel;
    private String shortBio;
    private int yearsOfExperience;
    private String socialLink;



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

    public String getSocialLink() {
        return socialLink;
    }

    public void setSocialLink(String socialLink) {
        this.socialLink = socialLink;
    }

}
