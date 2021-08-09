package com.example.casestudymodule4.model.entity;

import com.example.casestudymodule4.model.entity.Role;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "không được để trống!")
    @Size(min = 2,max = 30,message = "độ dài ít nhất là 2 và không vượt quá 30 kí tự")
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    @NotEmpty(message = "Thiếu password")
    @Min(value = 8, message = "Password phải từ 8 kí tự trở lên")
    private String password;

    @Column(nullable = false)
    @NotEmpty(message = "Thiếu name")
    private String fullName;

    private String address;

    @Column(nullable = false)
    private String email;

    private String numberPhone;

    @Column(nullable = false)
    private String gender;

    private String avatar;

    private Date dateOfBirth;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password, String fullName, String address, String email, String numberPhone, String gender, String avatar, Date dateOfBirth, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.numberPhone = numberPhone;
        this.gender = gender;
        this.avatar = avatar;
        this.dateOfBirth = dateOfBirth;
        this.roles = roles;
    }

    public User(String username, String password, String fullName ) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public String getAvatar() {
        return avatar;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Set<Role> getRoles() {
        return roles;
    }

}

