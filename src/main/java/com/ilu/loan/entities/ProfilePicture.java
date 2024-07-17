package com.ilu.loan.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_profile_picture")
public class ProfilePicture {
    @Id
    @GeneratedValue
    private String id;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Long size;

    @Column(name = "url")
    private String url;

    @OneToMany(mappedBy = "profilePicture")
    private List<Customer> customer;
}