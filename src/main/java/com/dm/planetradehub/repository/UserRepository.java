package com.dm.planetradehub.repository;

import com.dm.planetradehub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByEmail(String email);

    @Query("DELETE FROM User u WHERE u.email = :email")
    void deleteUserByEmail(String email);
}
