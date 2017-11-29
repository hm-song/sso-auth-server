package com.fourones.web.fsso.entity;

import com.fourones.web.fsso.service.authentication.type.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "OAUTH_CLIENT")
public class Client implements ClientDetails {

    @Id
    private String clientId;

    private String clientSecret;

    private String scope;

    private String authorities;

    public Client(String clientId, String clientSecret, String scope) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
    }

    public Client() {
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return new HashSet<>(Arrays.asList(scope));
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return new HashSet<>(Arrays.asList("authorization_code"));
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("DM_SVC_AUTH"));
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return 60*60*12; // 12 hours
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return 60*60*24*30; // 30 days
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

}
