package roll.be.geofind.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import roll.be.geofind.model.PasswordResetToken;
import roll.be.geofind.model.UserInscription;
import roll.be.geofind.repository.PasswordResetTokenRepository;
import roll.be.geofind.repository.UserRepositoryInscription;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final UserRepositoryInscription userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final TokenService tokenService;
    private final JavaMailSender mailSender;

    public PasswordResetService(UserRepositoryInscription userRepository,
                                PasswordResetTokenRepository tokenRepository,
                                TokenService tokenService,
                                JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.tokenService = tokenService;
        this.mailSender = mailSender;
    }

    public void createPasswordResetToken(String email) {
        UserInscription user = userRepository.findByEmail(email).orElseThrow();

        String rawToken = tokenService.generateToken();
        String hashedToken = tokenService.hashToken(rawToken);

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setUser(user);
        resetToken.setToken(hashedToken);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1));

        tokenRepository.save(resetToken);

        String resetLink = "http://localhost:8080/reset-password?token=" + rawToken + "&uid=" + user.getId();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Réinitialisation de mot de passe");
        message.setText("Cliquez ici pour réinitialiser : " + resetLink);
        mailSender.send(message);
    }
}

