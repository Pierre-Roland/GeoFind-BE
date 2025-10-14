package roll.be.geofind.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roll.be.geofind.model.LinkCountryUser;
import roll.be.geofind.service.ServiceLinkCountryUser;

import java.util.List;

@RestController
@RequestMapping("/userPage")
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerRestUserPage {

    final private ServiceLinkCountryUser serviceLinkCountryUser;

    public ControllerRestUserPage(ServiceLinkCountryUser serviceLinkCountryUser) {
        this.serviceLinkCountryUser = serviceLinkCountryUser;
    }

    @PostMapping("/save")
    public ResponseEntity<?> SaveCoordonnees(@RequestBody LinkCountryUser linkCountryUser) {
        try {
            LinkCountryUser saved = serviceLinkCountryUser.saveLinkCountryUser(linkCountryUser);
            if (saved.getUsername().equals("already exists")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<?> GetOneFav(@RequestBody LinkCountryUser linkCountryUser) {
        try {
            LinkCountryUser getResult = serviceLinkCountryUser.getOneLinkCountryUser(linkCountryUser);
            if (getResult == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Impossible de trouver ce link country user : " + linkCountryUser);
            }
            return ResponseEntity.status(HttpStatus.OK).body(getResult);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Impossible de trouver ce link country user : " + e.getMessage());
        }
    }

    @GetMapping("{username}")
    public ResponseEntity<?> GetAllFavOfOneUser(@PathVariable String username) {
        try {
            List<LinkCountryUser> getResult = serviceLinkCountryUser.getAllLinkCountryOfUser(username);
            List<String> countries = getResult.stream()
                    .map(LinkCountryUser::getCountry)
                    .toList();
            return ResponseEntity.ok(countries);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Impossible de trouver ce link country user : " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteOneFav(@RequestBody LinkCountryUser linkCountryUser) {
        try {
            serviceLinkCountryUser.deleteLinkCountryUser(linkCountryUser);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Favori supprimé : " + linkCountryUser.getCountry() + " pour l'utilisateur " + linkCountryUser.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression du favori : " + e.getMessage());
        }
    }
}
