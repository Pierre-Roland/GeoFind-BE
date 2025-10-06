package roll.be.geofind.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roll.be.geofind.model.User;
import roll.be.geofind.model.UserInscription;
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
    public ResponseEntity<UserInscription> SaveUser(@RequestBody UserInscription user) {
        user.setAdmin(false);
        UserInscription saved = serviceConnection.saveUser(user);
        System.out.println(saved);
        if (saved.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(user);
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        }
    }

    @GetMapping("/identification")
    public ResponseEntity<Boolean> getUser(@RequestBody User user ) {
        user.setAdmin(false);
        boolean userInDb = serviceConnection.verifyLogin(user.getUsername(), user.getPassword());
        System.out.println(userInDb);
        if (!userInDb) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(true);
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
