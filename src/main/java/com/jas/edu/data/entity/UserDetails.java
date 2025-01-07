package com.jas.edu.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Persistable;

@IdClass(UserDetailsPk.class )
@Entity
@Data
@Table(name="userdetails")
public class UserDetails  implements Persistable<UserDetailsPk> {
    @Id
    @NotBlank
    @Length(min = 1, max = 16)
    private String firstName;

    @NotBlank
    @Length(min = 1, max = 16)
    private String lastName;

    @Id
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "\\+?(91?|0?)[\\-\\s]?[3-9]\\d{3}[\\-\\s]?\\d{6}$", message = "{Invalid phone number}")
    private String contactNo;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String address;

    @NotBlank
    private String postalCode;

    private boolean allowsMarketing;

    @Transient
    private boolean update;

    @NotBlank
    @Length(min = 1, max = 16)
    private String userName;

    // FIXME Passwords should never be stored in plain text!
    @Size(min = 8, max = 12, message = "Password must be min 8 to max 12 char long")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
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

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}
    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public boolean isAllowsMarketing() {return allowsMarketing;}

    public void setAllowsMarketing(boolean allowsMarketing) {this.allowsMarketing = allowsMarketing;}

    @Override
    public UserDetailsPk getId() {
        return new UserDetailsPk(this.firstName,this.lastName);
    }

    @Override
    public boolean isNew() {
        return !this.update;
    }
}