package pl.adudkiewicz.security;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class JwtAuthentication implements Authentication
{
    private static final long serialVersionUID = 1L;
    private String tokenContent;
    private boolean authenticated;

    public JwtAuthentication(String token)
    {
        this.tokenContent = token;
    }
    
    @Override
    public String getName()
    {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @Override
    public Object getCredentials()
    {
        return null;
    }

    @Override
    public Object getDetails()
    {
        return tokenContent;
    }

    @Override
    public Object getPrincipal()
    {
        return null;
    }

    @Override
    public boolean isAuthenticated()
    {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean auth) throws IllegalArgumentException
    {
        this.authenticated = auth;
    }

}
