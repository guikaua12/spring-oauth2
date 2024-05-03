package me.approximations.security.jwt.token;

import lombok.AllArgsConstructor;
import me.approximations.security.entities.SecurityUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
public class JwtAuthenticationToken implements Authentication {
    private final SecurityUserDetails securityUserDetails;
    private boolean authenticated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return securityUserDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return securityUserDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return this.securityUserDetails.getUsername();
    }
}
