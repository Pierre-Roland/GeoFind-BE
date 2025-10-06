package roll.be.geofind.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import roll.be.geofind.model.User;
import roll.be.geofind.model.UserInscription;
import roll.be.geofind.repository.UserRepository;
import roll.be.geofind.repository.UserRepositoryInscription;

@Service
public class ServiceConnection {

    UserInscription userInscriptionNotFound = new UserInscription();
    User userNotFound = new User();
    User userFound = new User();

    private final UserRepository userRepository;
    private final UserRepositoryInscription userRepositoryInscription;
    private final PasswordEncoder passwordEncoder;

    public ServiceConnection(final UserRepository userRepository, UserRepositoryInscription userRepositoryInscription, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRepositoryInscription = userRepositoryInscription;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(Long id) {
        return userRepository.findUserById(id);
    }

    public UserInscription saveUser(UserInscription user) {
        User givenUser = getUserByUsername(user.getUsername());
        if (givenUser == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepositoryInscription.save(user);
        }
        else {
            return userInscriptionNotFound;
        }
    }

    public User deleteUser(Long id) {
        User givenUser = getUser(id);
        userFound.setPassword("found");
        if (givenUser != null) {
            userRepository.delete(givenUser);
            return userFound;
        }
        else {
            return userNotFound;
        }
    }

    public boolean verifyLogin(String username, String password) {
        User givenUser = getUserByUsername(username);
        if (givenUser == null) {
            return false;
        }
        return passwordEncoder.matches(password, givenUser.getPassword());
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
