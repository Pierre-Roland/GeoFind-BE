package roll.be.geofind.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roll.be.geofind.model.User;
import roll.be.geofind.service.ServiceConnection;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerRestConnection {
    private final ServiceConnection serviceConnection;

    public ControllerRestConnection(ServiceConnection serviceConnection) {
        this.serviceConnection = serviceConnection;
    }

    @PostMapping("/save")
    public ResponseEntity<User> SaveUser(@RequestBody User user) {
        User saved = serviceConnection.saveUser(user);
        if (saved.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(saved);
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id ) {
        User userInDb = serviceConnection.getUser(id);
        if (userInDb == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(userInDb);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> DeleteCoordonnees(@PathVariable Long id ) {
        User deleted = serviceConnection.deleteUser(id);
        if (deleted.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }
}
