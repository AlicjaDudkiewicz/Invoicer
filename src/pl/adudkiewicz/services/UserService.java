package pl.adudkiewicz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.adudkiewicz.model.User;
import pl.adudkiewicz.repositories.UserRepository;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;

    public User verifyUser(User inputUser)
    {

        User user = userRepository.findOne(inputUser.getUsername());
        if (user != null)
        {
            verifyPassword(inputUser, user);
            return user;
        }
        return null;
    }

    private boolean verifyPassword(User inputUser, User user)
    {
        String correctPassword = user.getPassword();
        String givenPassword = inputUser.getPassword();
        if (correctPassword.equals(givenPassword))
        {
            return true;
        }
        return false;
    }
}
