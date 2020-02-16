package pl.bjosek.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bjosek.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}