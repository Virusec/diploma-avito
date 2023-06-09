package com.example.diploma.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author anna
 */
@Entity
@Table(name = "ads")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdEntity {
    @Id
    @Column(name = "ad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity author;
    private String title;
    private int price;
    private String description;
}
