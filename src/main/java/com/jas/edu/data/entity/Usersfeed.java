package com.jas.edu.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@IdClass(UserfeedPK.class )
@Entity
@Data
@Table(name="feedbackdatas")
public class Usersfeed {
    @Id
    @NotBlank
    @Length(min = 1, max = 16)
    private String firstName;
    @NotBlank
    @Length(min = 1, max = 16)
    private String lastName;
    @Id
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Pattern(regexp = "\\+?(91?|0?)[\\-\\s]?[3-9]\\d{3}[\\-\\s]?\\d{6}$", message = "{Invalid phone number}")
    private String contactNo;
    @NotBlank
    private String ratingGroup;
    @Column(columnDefinition = "TEXT")
    private String address;
    @NotBlank
    private String postalCode;
    @NotBlank
    @NotNull
    private String suggestions;

    public Usersfeed() {
    }

    public Usersfeed(String firstName, String lastName, String email, String contactNo, String ratingGroup, String address, String postalCode, String suggestions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.ratingGroup = ratingGroup;
        this.address = address;
        this.postalCode = postalCode;
        this.suggestions = suggestions;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getRatingGroup() {
        return ratingGroup;
    }

    public void setRatingGroup(String ratingGroup) {
        this.ratingGroup = ratingGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }
}
