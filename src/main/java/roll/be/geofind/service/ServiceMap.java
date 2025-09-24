package roll.be.geofind.service;

import org.springframework.stereotype.Service;
import roll.be.geofind.model.Coordonnees;
import roll.be.geofind.repository.RepositoryMap;

@Service
public class ServiceMap {

    private final RepositoryMap repositoryMap;

    public ServiceMap(RepositoryMap repositoryMap) {this.repositoryMap = repositoryMap;}

    public Coordonnees SaveCoordonnees(Coordonnees coordonnees) {
        return repositoryMap.save(coordonnees);
    }
}
