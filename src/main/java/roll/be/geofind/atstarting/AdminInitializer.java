package roll.be.geofind.atstarting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import roll.be.geofind.model.User;
import roll.be.geofind.repository.UserRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.countByAdminTrue() == 0) {
            User admin1 = new User();
            admin1.setUsername("Pierre");
            admin1.setPassword(passwordEncoder.encode("11adeline11"));
            admin1.setAdmin(true);

            User admin2 = new User();
            admin2.setUsername("Alice");
            admin2.setPassword(passwordEncoder.encode("11alice11"));
            admin2.setAdmin(true);

            userRepository.save(admin1);
            userRepository.save(admin2);

            System.out.println("Admins initiaux créés !");
        }
    }
}
