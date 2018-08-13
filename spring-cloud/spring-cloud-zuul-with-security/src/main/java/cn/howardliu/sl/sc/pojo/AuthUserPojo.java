package cn.howardliu.sl.sc.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * <br/>create at 16-2-25
 *
 * @author liuxh
 * @since 1.0.0
 */
public class AuthUserPojo implements UserDetails {
    private Long userId;
    private String username;
    private String password;
    private boolean enabled;
    private List<AuthGroupPojo> groups = new ArrayList<>();
    private final Set<GrantedAuthority> authorities = Collections.synchronizedSet(new HashSet<>());

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<AuthGroupPojo> getGroups() {
        return groups;
    }

    public void setGroups(List<AuthGroupPojo> groups) {
        this.groups = groups;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
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
        return enabled;
    }

    @Override
    public boolean equals(Object rhs) {
        return (rhs instanceof AuthUserPojo) && (username.equals(((AuthUserPojo) rhs).username));
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "AuthUserPojo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", groups=" + groups +
                ", authorities=" + authorities +
                '}';
    }
}
