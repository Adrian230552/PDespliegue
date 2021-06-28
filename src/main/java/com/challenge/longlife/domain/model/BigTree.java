package com.challenge.longlife.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trees")
public class BigTree extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @Column(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String gender;

    private Date bornAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<GreenLeaf> greenLeaves;

    @OneToMany(fetch = FetchType.LAZY)
    private List<SapDrop> sapDrops;

    public BigTree() {
    }

    public BigTree(@NotNull String username, @NotNull String email, String firstName, String lastName, String gender, Date bornAt) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.bornAt = bornAt;
    }

    public Long getId() {
        return id;
    }

    public BigTree setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public BigTree setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BigTree setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public BigTree setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BigTree setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public BigTree setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Date getBornAt() {
        return bornAt;
    }

    public BigTree setBornAt(Date bornAt) {
        this.bornAt = bornAt;
        return this;
    }

    public List<GreenLeaf> getGreenLeaves() {
        return greenLeaves;
    }

    public BigTree setGreenLeaves(List<GreenLeaf> greenLeaves) {
        this.greenLeaves = greenLeaves;
        return this;
    }

    public List<SapDrop> getSapDrops() {
        return sapDrops;
    }

    public BigTree setSapDrops(List<SapDrop> sapDrops) {
        this.sapDrops = sapDrops;
        return this;
    }
}
