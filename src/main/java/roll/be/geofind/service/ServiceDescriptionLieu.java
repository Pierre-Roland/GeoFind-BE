package roll.be.geofind.service;

import org.springframework.stereotype.Service;
import roll.be.geofind.model.Coordonnees;
import roll.be.geofind.model.DescriptionLieu;
import roll.be.geofind.repository.DescriptionLieuRepository;
import roll.be.geofind.repository.RepositoryMap;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceDescriptionLieu {

    private final RepositoryMap repositoryMap;

    private final DescriptionLieuRepository descriptionLieuRepository;

    public ServiceDescriptionLieu(RepositoryMap repositoryMap, DescriptionLieuRepository descriptionLieuRepository) {
        this.repositoryMap = repositoryMap;
        this.descriptionLieuRepository = descriptionLieuRepository;
    }

    public DescriptionLieu getDescriptionLieu(String lieu) {
        return descriptionLieuRepository.findByLieu(lieu);
    }

    public List<DescriptionLieu> getMostVisitedDescriptionLieus(int limit) {
        List<DescriptionLieu> ListeDesPaysLesPlusVisite = new ArrayList<>();
        List<Coordonnees> listMostTimesVisitedCountry = repositoryMap.findAllByOrderByTimesVisitedDesc();
        for (int i = 0; i < limit; i++) {
            String country = listMostTimesVisitedCountry.get(i).getCountry();
            DescriptionLieu descriptionLieu = getDescriptionLieu(country);
            ListeDesPaysLesPlusVisite.add(descriptionLieu);
        }
        return ListeDesPaysLesPlusVisite;
    }
}
