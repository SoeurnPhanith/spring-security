package com.example.Spring_Security_OAuth2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_oauth2_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider; // google / github
    private String providerId;
    private String email;
    private String picture;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
