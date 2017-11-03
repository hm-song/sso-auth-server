package com.fourones.web.fsso.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oauth_access_token")
@Data
public class AccessToken {
    //
    @Id
    private String tokenId;

    private String token;

    @Column(name="user_name")
    private String userName;

    @Column(name="authentication_id")
    private String authenticationId;

    @Column(name="client_id")
    private String clientId;

    private String authentication;

}
