package roll.be.geofind.service;

import org.springframework.stereotype.Service;
import roll.be.geofind.model.User;
import roll.be.geofind.repository.UserRepository;

@Service
public class ServiceConnection {

    User userNotFound = new User();
    User userFound = new User();

    private final UserRepository userRepository;

    public ServiceConnection(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        return userRepository.findByIdUser(id);
    }

    public User saveUser(User user) {
        User givenUser = getUser(user.getId());
        if (givenUser == null) {
            return userRepository.save(user);
        }
        else {
            return userFound;
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
}
