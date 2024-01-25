package hellowoori.backendproproject.domain.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u.id, u.nickname FROM User u WHERE u.id IN :userIds")
    List<Object[]> findNicknamesByUserIds(@Param("userIds") Set<UUID> userIds);
}
