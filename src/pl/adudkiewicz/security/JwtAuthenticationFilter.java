package pl.adudkiewicz.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter
{
    @Autowired
    JwtAuthenticationProvider authenticationProvider;
    
    public JwtAuthenticationFilter()
    {
        super("/**");
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request,
            HttpServletResponse response)
    {
        return true;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException
    {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer "))
        {
            throw new JwtAuthenticationException("No JWT token found in request headers");
        }
       
        String token = header.substring(7);
        
        JwtAuthentication jwt = new JwtAuthentication(token);

        return authenticationProvider.authenticate(jwt);
    }

    public JwtAuthenticationProvider getAuthenticationProvider()
    {
        return authenticationProvider;
    }

    public void setAuthenticationProvider(JwtAuthenticationProvider authenticationProvider)
    {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException
    {
        super.successfulAuthentication(request, response, chain, authResult);

        chain.doFilter(request, response);
    }
}