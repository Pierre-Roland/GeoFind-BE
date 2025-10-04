package roll.be.geofind.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roll.be.geofind.model.Coordonnees;
import roll.be.geofind.service.ServiceMap;

@RestController
@RequestMapping("/maps")
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerRestMap {

    private final ServiceMap serviceMap;

    public ControllerRestMap(ServiceMap serviceMap) {
        this.serviceMap = serviceMap;
    }

    @PostMapping("/save")
    public ResponseEntity<Coordonnees> SaveCoordonnees(@RequestBody Coordonnees coordonnees) {
        Coordonnees saved = serviceMap.saveCoordonnees(coordonnees, coordonnees.getCountry());
        if (saved.getCountry().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(saved);
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        }
    }

    @GetMapping("/{country}")
    public ResponseEntity<Coordonnees> getCoordonneesCountry(@PathVariable String country ) {
        Coordonnees countryInDB = serviceMap.getCoordonnees(country);
        if (countryInDB == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(countryInDB);
        }
    }

    @DeleteMapping("/{country}")
    public ResponseEntity<Coordonnees> DeleteCoordonnees(@PathVariable String country ) {
        Coordonnees deleted = serviceMap.deleteCoordonnees(country);
        if (deleted.getCountry().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
}
