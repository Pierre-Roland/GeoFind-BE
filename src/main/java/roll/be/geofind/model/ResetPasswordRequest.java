package roll.be.geofind.model;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private Long uid;
    private String token;
    private String newPassword;
}
