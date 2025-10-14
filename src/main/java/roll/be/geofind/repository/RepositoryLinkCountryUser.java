package roll.be.geofind.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roll.be.geofind.model.LinkCountryUser;

import java.util.List;

@Repository
public interface RepositoryLinkCountryUser extends JpaRepository<LinkCountryUser, Long> {

    LinkCountryUser findByUsernameAndCountry(String username, String country);

    List<LinkCountryUser> findAllByUsername(String username);

    @Transactional
    void deleteByUsernameAndCountry(String username, String country);
}
