package com.swlab.webapp.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityUser extends User implements UserDetails {

    private static final long serialVersionUID = -5037302487252709171L;

    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialNonExpired;
    private final boolean enabled;

    public SecurityUser (User user) {
        super();
        setId(user.getId());
        setEmail(user.getEmail());
        setName(user.getName());
        setPassword(user.getPassword());
        setDel(user.isDel());
        setUserRoles(user.getUserRoles());
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this. credentialNonExpired = true;
        this.enabled = true;
    }

    public Set<UserRole.RoleType> getRoleTypes() {
        return getUserRoles().stream().map(f -> f.getRoleName()).collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getUserRoles();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
