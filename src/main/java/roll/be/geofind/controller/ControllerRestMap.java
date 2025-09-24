package roll.be.geofind.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roll.be.geofind.model.Coordonnees;
import roll.be.geofind.service.ServiceMap;

import java.util.List;

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
        System.out.println("Received: " + coordonnees.getId());
        System.out.println("Received: " + coordonnees.getZoom());
        System.out.println("Received: " + coordonnees.getCenter1());
        System.out.println("Received: " + coordonnees.getCenter2());
        System.out.println("Received: " + coordonnees.getCountry());
        Coordonnees saved = serviceMap.SaveCoordonnees(coordonnees);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> DeleteCoordonnees(@PathVariable Long id ) {
//
//        return ResponseEntity
//                .status(HttpStatus.NO_CONTENT)
//                .build();
//    }
//    @GetMapping("{id}")
//    public ResponseEntity<List<CommentaireResponse>> getCoordonneesCountry(@PathVariable  ) {
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(commentaireResponses);
//    }
}
