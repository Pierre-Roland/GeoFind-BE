package roll.be.geofind.repository;

import org.springframework.data.repository.CrudRepository;
import roll.be.geofind.model.PasswordResetToken;
import roll.be.geofind.model.UserInscription;

import java.util.Optional;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {
    // Méthode pour récupérer le token d’un utilisateur
    Optional<PasswordResetToken> findByUser(UserInscription user);

    // Optionnel : si tu veux vérifier par token aussi
    Optional<PasswordResetToken> findByToken(String token);

    void deleteAllByUser(UserInscription user);
}
