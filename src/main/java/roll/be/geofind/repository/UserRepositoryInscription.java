package roll.be.geofind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roll.be.geofind.model.User;
import roll.be.geofind.model.UserInscription;

import java.util.Optional;

@Repository
public interface UserRepositoryInscription extends JpaRepository<UserInscription, Long> {

    long countByAdminTrue();

    Optional<UserInscription> findByEmail(String email);
}
