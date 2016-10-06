package pl.adudkiewicz.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.adudkiewicz.model.User;
import pl.adudkiewicz.repositories.UserRepository;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public User verifyUser(User inputUser)
    {

        User user = userRepository.findOne(inputUser.getUsername());
        if (user != null && verifyPassword(inputUser, user))
        {
            verifyPassword(inputUser, user);
            log.info("User: " + inputUser.getUsername() + "has been verified successfully.");
            return user;
        }
        log.warn("User" + inputUser.getUsername() + " verify operation has been failed.");
        return null;
    }

    private boolean verifyPassword(User inputUser, User user)
    {
        String correctPassword = user.getPassword();
        String givenPassword = inputUser.getPassword();
        String passwordDigest = DigestUtils.sha256Hex(givenPassword);
        if (correctPassword.equals(passwordDigest))
        {
            log.info("Password for user: " + inputUser.getUsername()
                    + " has been verified successfully.");
            return true;
        }
        log.warn("Password for user: " +inputUser.getUsername()+" is incorrect.");
        return false;
    }
}
