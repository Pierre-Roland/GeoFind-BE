package roll.be.geofind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roll.be.geofind.model.UserInscription;

@Repository
public interface UserRepositoryInscription extends JpaRepository<UserInscription, Long> {

    long countByAdminTrue();
}
