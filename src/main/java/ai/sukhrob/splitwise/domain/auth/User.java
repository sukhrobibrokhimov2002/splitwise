package ai.sukhrob.splitwise.domain.auth;

import ai.sukhrob.splitwise.domain.Auditable;
import ai.sukhrob.splitwise.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends Auditable implements UserDetails {
    @Column(name = "first_name")
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Role userType;
    @Column(unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phone", unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    @ManyToMany
    private List<Roles> roles;


    boolean accountNonExpired=true;
    boolean accountNonLocked=true;
    boolean credentialsNonExpired=true;
    boolean enabled=true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<User> friends=null;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return phone;
    }


}
