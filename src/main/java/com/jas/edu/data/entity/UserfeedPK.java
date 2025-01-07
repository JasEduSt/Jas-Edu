package com.jas.edu.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
public class UserfeedPK implements Serializable {
    private String firstName;
    private String email;

    public UserfeedPK(String firstName, String email) {
        this.firstName = firstName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserfeedPK userfeedPK)) return false;
        return firstName == userfeedPK.firstName && Objects.equals(email, userfeedPK.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, email);
    }
}
