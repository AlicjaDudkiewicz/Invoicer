package pl.adudkiewicz.security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider
{

    @Override
    public Authentication authenticate(Authentication token) throws AuthenticationException
    {
        JWTVerifier jwtVerifier= new JWTVerifier("test123");
        
        try
        {
            jwtVerifier.verify((String) token.getDetails());
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | IllegalStateException
                | SignatureException | JWTVerifyException e)
        {
            e.printStackTrace();
            throw new JwtAuthenticationException("Invalid token");
        } 
        token.setAuthenticated(true);
        return token;
    }

    @Override
    public boolean supports(Class<?> arg0)
    {
        return true;
    }

}
