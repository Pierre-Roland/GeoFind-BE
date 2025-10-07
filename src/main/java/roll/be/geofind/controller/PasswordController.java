package roll.be.geofind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import roll.be.geofind.model.PasswordResetToken;
import roll.be.geofind.model.ResetPasswordRequest;
import roll.be.geofind.model.UserInscription;
import roll.be.geofind.repository.PasswordResetTokenRepository;
import roll.be.geofind.repository.UserRepositoryInscription;
import roll.be.geofind.service.PasswordResetService;
import roll.be.geofind.service.TokenService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class PasswordController {

    @Autowired
    private JavaMailSender mailSender;

    private final PasswordResetService resetService;
    private final TokenService tokenService;
    private final UserRepositoryInscription userRepositoryInscription;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordController(PasswordResetService resetService, TokenService tokenService, UserRepositoryInscription userRepositoryInscription, PasswordResetTokenRepository passwordResetTokenRepository) {
        this.resetService = resetService;
        this.tokenService = tokenService;
        this.userRepositoryInscription = userRepositoryInscription;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        try {
            resetService.createPasswordResetToken(email);
        } catch (Exception ignored) {}
        return ResponseEntity.ok("Si l’adresse est valide, un email a été envoyé.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        UserInscription user = userRepositoryInscription.findById(request.getUid()).orElseThrow();
        PasswordResetToken resetToken = passwordResetTokenRepository.findByUser(user)
                .orElseThrow();

        String hashedToken = tokenService.hashToken(request.getToken());

        if (!resetToken.getToken().equals(hashedToken) || resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token invalide ou expiré");
        }

        user.setPassword(new BCryptPasswordEncoder().encode(request.getNewPassword()));
        userRepositoryInscription.save(user);

        passwordResetTokenRepository.delete(resetToken);

        return ResponseEntity.ok("Mot de passe réinitialisé avec succès !");
    }

    @GetMapping("/test-mail")
    public String testMail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("pierreroll04@gmail.com");
            message.setTo("pierreroll04@gmail.com");
            message.setSubject("Test SendGrid");
            message.setText("Ceci est un test de SendGrid via Spring Boot.");
            mailSender.send(message);
            return "Mail envoyé avec succès !";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur : " + e.getMessage();
        }
    }
}

