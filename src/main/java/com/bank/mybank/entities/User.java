package com.bank.mybank.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private int id;

    @UniqueElements(message = "Account number is exist")
    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @UniqueElements(message = "User name is exist")
    @Column(name = "username", unique = true)
    private String username;

    @Length(min = 6, message = "Password must be 6 to 20 character")
    @Column(name = "Password", nullable = true)
    private String password;

    @Column(name = "Name", length = 20, nullable = false)
    private String name;

    @Column(name = "Address")
    private String address;

    @Column(name = "balance")
    private double balane;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private Set<Role> roles = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", balane=" + balane +
                ", roles=" + roles +
                '}';
    }
}
