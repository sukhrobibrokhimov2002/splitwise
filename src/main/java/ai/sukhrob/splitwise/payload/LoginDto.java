package ai.sukhrob.splitwise.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String phoneNumber;
    private String password;
}
