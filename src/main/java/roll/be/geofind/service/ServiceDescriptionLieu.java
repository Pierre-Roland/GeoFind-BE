package roll.be.geofind.service;

import org.springframework.stereotype.Service;
import roll.be.geofind.model.DescriptionLieu;
import roll.be.geofind.repository.DescriptionLieuRepository;

@Service
public class ServiceDescriptionLieu {

    private final DescriptionLieuRepository descriptionLieuRepository;

    public ServiceDescriptionLieu(DescriptionLieuRepository descriptionLieuRepository) {
        this.descriptionLieuRepository = descriptionLieuRepository;
    }

    public DescriptionLieu getDescriptionLieu(String lieu) {
        return descriptionLieuRepository.findByLieu(lieu);
    }
}
