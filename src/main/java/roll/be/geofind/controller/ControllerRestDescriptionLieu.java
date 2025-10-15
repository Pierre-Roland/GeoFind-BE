package roll.be.geofind.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roll.be.geofind.model.DescriptionLieu;
import roll.be.geofind.service.ServiceDescriptionLieu;

import java.util.List;

@RestController
@RequestMapping("/description")
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerRestDescriptionLieu {

    private final ServiceDescriptionLieu serviceDescriptionLieu;

    public ControllerRestDescriptionLieu(ServiceDescriptionLieu serviceDescriptionLieu) {
        this.serviceDescriptionLieu = serviceDescriptionLieu;
    }

    @GetMapping("/{lieu}")
    public ResponseEntity<DescriptionLieu> getDescription(@PathVariable String lieu) {
        DescriptionLieu descriptionLieu = serviceDescriptionLieu.getDescriptionLieu(lieu);
        if (descriptionLieu == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(descriptionLieu);
    }

    @GetMapping("/lieu/mostVisited/{numberPagination}")
    public ResponseEntity<List<DescriptionLieu>> getMostVisited(@PathVariable int numberPagination) {
        List<DescriptionLieu> descriptionLieu = serviceDescriptionLieu.getMostVisitedDescriptionLieus(numberPagination);
        if (descriptionLieu == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(descriptionLieu);
    }
}
