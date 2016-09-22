package pl.adudkiewicz.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWTSigner;

import pl.adudkiewicz.model.User;
import pl.adudkiewicz.services.UserService;

@RestController
@RequestMapping("login")
@CrossOrigin("*")
public class LoginController
{
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<String> verifyUser(@RequestBody @Valid User inputUser)
    {
        User user = userService.verifyUser(inputUser);
        if (user != null)
        {
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", user.getUsername());
            claims.put("email", user.getMail());

            JWTSigner signer = new JWTSigner("test123");
            String token = signer.sign(claims);

            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }
}
