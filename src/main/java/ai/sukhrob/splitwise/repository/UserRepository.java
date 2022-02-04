package ai.sukhrob.splitwise.repository;

import ai.sukhrob.splitwise.domain.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByPhone(String phone);
}
