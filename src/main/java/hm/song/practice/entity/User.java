package hm.song.practice.entity;

import hm.song.practice.entity.converter.StringToAuthoritiesConverter;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "USER")
@Data
public class User implements UserDetails {

    @Id
    private String id;

    private String username;

    private String password;

    @Convert(converter = StringToAuthoritiesConverter.class)
    private List<GrantedAuthority> role;

    public User(String id, String username, String password, List<GrantedAuthority> role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
