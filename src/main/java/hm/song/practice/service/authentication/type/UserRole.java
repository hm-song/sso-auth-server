package hm.song.practice.service.authentication.type;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by hm.song on 2017. 11. 1..
 */
public enum UserRole implements GrantedAuthority {

    USER("ROLE_USER"),

    ADMIN("ROLE_ADMIN");

    private String authority;


    UserRole(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
