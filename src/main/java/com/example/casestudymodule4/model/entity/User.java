package com.example.casestudymodule4.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    private static final Avatar a = new Avatar("https://scontent.fhph2-1.fna.fbcdn.net/v/t1.30497-1/143086968_2856368904622192_1959732218791162458_n.png?_nc_cat=1&ccb=1-4&_nc_sid=7206a8&_nc_ohc=ascCGU_Zw6wAX8dIzZZ&_nc_ht=scontent.fhph2-1.fna&oh=77bf564c386cc2e30e1c8b21c7499ff8&oe=61370CF8");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "không được để trống!")
    @Size(min = 2, max = 30, message = "độ dài ít nhất là 2 và không vượt quá 30 kí tự")
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


    private Date dateOfBirth;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;
    private boolean enabled=true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public User() {
    }

    public User(Long id, String username, String password, String fullName, String address, String email, String numberPhone, String gender, Date dateOfBirth, Set<Role> roles, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.numberPhone = numberPhone;
        this.gender = gender;

        this.dateOfBirth = dateOfBirth;
        this.roles = roles;
        this.enabled = enabled;
    }



}

