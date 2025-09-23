package roll.be.geofind.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentaires")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerRestMap {

    @PostMapping("/save")
    public ResponseEntity<Void> SaveCommentaire(@Valid @RequestBody  ) {

        String url = "http://localhost:9090/commentaires/save";
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("location", url)
                .build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteCommentaire(@PathVariable Long id ) {

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
    @GetMapping("{id}")
    public ResponseEntity<List<CommentaireResponse>> getCommentairesById(@PathVariable  ) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentaireResponses);
    }
}
