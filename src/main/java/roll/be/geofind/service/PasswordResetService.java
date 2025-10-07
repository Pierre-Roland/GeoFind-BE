package roll.be.geofind.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import roll.be.geofind.model.PasswordResetToken;
import roll.be.geofind.model.UserInscription;
import roll.be.geofind.repository.PasswordResetTokenRepository;
import roll.be.geofind.repository.UserRepositoryInscription;

import java.time.LocalDateTime;

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
        System.out.println("Creating password reset token");
        UserInscription user = userRepository.findByEmail(email).orElseThrow();

        //tokenRepository.deleteAllByUser(user);

        String rawToken = tokenService.generateToken();
        String hashedToken = tokenService.hashToken(rawToken);

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setUser(user);
        resetToken.setToken(hashedToken);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1));

        tokenRepository.save(resetToken);

        String resetLink = "http://localhost:4200/reset-password?token=" + rawToken + "&uid=" + user.getId();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pierreroll04@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Réinitialisation de mot de passe");
        message.setText("Cliquez ici pour réinitialiser : " + resetLink);
        mailSender.send(message);
    }
}

