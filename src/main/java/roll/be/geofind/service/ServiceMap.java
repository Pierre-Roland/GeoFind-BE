package roll.be.geofind.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import roll.be.geofind.model.Coordonnees;
import roll.be.geofind.repository.RepositoryMap;

@Service
public class ServiceMap {

    Coordonnees countryNotFound = new Coordonnees();

    Coordonnees countryFound = new Coordonnees();

    private final RepositoryMap repositoryMap;


    public ServiceMap(RepositoryMap repositoryMap) {this.repositoryMap = repositoryMap;}

    @Transactional
    public Coordonnees saveCoordonnees(Coordonnees coordonnees, String country) {
        countryNotFound.setCountry("");
        Coordonnees countryInDB = getCoordonnees(country);
        if (countryInDB == null) {
            return repositoryMap.save(coordonnees);
        }
        else {
            return countryNotFound;
        }
    }

    @Transactional
    public Coordonnees getCoordonnees(String country) {
        Coordonnees countryInDb = repositoryMap.findByCountry(country);
        if (countryInDb != null) {
            countryInDb.setTimesVisited(countryInDb.getTimesVisited() + 1);
        }
        return countryInDb;
    }

    @Transactional
    public Coordonnees deleteCoordonnees(String country) {
        countryNotFound.setCountry("");
        countryFound.setCountry("found");
        Coordonnees countryInDB = getCoordonnees(country);
        if (countryInDB != null) {
            repositoryMap.deleteByCountry(country);
            return countryFound;
        }
        else {
            return countryNotFound;
        }
    }
}
