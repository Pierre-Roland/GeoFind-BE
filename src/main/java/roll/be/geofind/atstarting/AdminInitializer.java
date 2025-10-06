package roll.be.geofind.atstarting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import roll.be.geofind.model.UserInscription;
import roll.be.geofind.repository.UserRepositoryInscription;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserRepositoryInscription userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UserRepositoryInscription userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.countByAdminTrue() == 0) {
            UserInscription admin1 = new UserInscription();
            admin1.setUsername("Pierre");
            admin1.setPassword(passwordEncoder.encode("11adeline11"));
            admin1.setEmail("pierreroll04@gmail.com");
            admin1.setAdmin(true);

            UserInscription admin2 = new UserInscription();
            admin2.setUsername("Alice");
            admin2.setPassword(passwordEncoder.encode("11alice11"));
            admin2.setEmail("lepierre04@gmail.com");
            admin2.setAdmin(true);

            userRepository.save(admin1);
            userRepository.save(admin2);

            System.out.println("Admins initiaux créés !");
        }
    }
}
