package app.boot.boottest.model;

import java.util.Objects;

public class User {
    private final String userName;
    private final String email;

    @Override
    public int hashCode() {
        return Objects.hash(userName, email);
    }

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUsername() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(email, user.email);
    }
}
