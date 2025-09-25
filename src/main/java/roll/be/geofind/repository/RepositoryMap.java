package roll.be.geofind.repository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roll.be.geofind.model.Coordonnees;

@Repository
public interface RepositoryMap extends JpaRepository<Coordonnees, Long> {

    Coordonnees findByCountry(String country);

    @Transactional
    void deleteByCountry(String country);
}
