package roll.be.geofind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roll.be.geofind.model.DescriptionLieu;

@Repository
public interface DescriptionLieuRepository extends JpaRepository<DescriptionLieu, Long> {

    DescriptionLieu findByLieu(String lieu);
}
