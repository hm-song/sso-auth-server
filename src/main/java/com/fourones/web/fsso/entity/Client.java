package com.fourones.web.fsso.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oauth_client_details")
@Data
public class Client {
    //
    @Id
    @Column(name="client_id")
    private String clientId;

    @Column(name="web_server_redirect_uri")
    private String redirectUri;

    @Column(name="logout_uri")
    private String logoutUri;

    @Column(name="base_uri")
    private String baseUri;

// getter/setter
}