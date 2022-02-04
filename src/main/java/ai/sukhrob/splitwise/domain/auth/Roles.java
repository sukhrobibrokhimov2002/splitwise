package ai.sukhrob.splitwise.domain.auth;

import ai.sukhrob.splitwise.domain.Auditable;
import ai.sukhrob.splitwise.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Roles extends Auditable implements GrantedAuthority {

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private Role roleName;



    @Override
    public String getAuthority() {
        return String.valueOf(this.roleName);
    }
}
