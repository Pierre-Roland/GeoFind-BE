package roll.be.geofind.service;

import org.springframework.stereotype.Service;
import roll.be.geofind.model.LinkCountryUser;
import roll.be.geofind.repository.RepositoryLinkCountryUser;

import java.util.List;

@Service
public class ServiceLinkCountryUser {
    private final RepositoryLinkCountryUser repositoryLinkCountryUser;

    public ServiceLinkCountryUser(RepositoryLinkCountryUser repositoryLinkCountryUser) {
        this.repositoryLinkCountryUser = repositoryLinkCountryUser;
    }

    public LinkCountryUser saveLinkCountryUser(LinkCountryUser linkCountryUser) {
        LinkCountryUser getLinkCountryUser = getOneLinkCountryUser(linkCountryUser);
        if (getLinkCountryUser != null) {
            getLinkCountryUser.setUsername("already exists");
            return getLinkCountryUser;
        }
        return this.repositoryLinkCountryUser.save(linkCountryUser);
    }

    public LinkCountryUser getOneLinkCountryUser(LinkCountryUser linkCountryUser) {
        return this.repositoryLinkCountryUser.findByUsernameAndCountry(linkCountryUser.getUsername(), linkCountryUser.getCountry());
    }

    public List<LinkCountryUser> getAllLinkCountryOfUser(String username) {
        return this.repositoryLinkCountryUser.findAllByUsername(username);
    }
}
