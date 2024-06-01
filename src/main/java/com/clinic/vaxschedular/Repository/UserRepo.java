package com.clinic.vaxschedular.Repository;

import com.clinic.vaxschedular.Entity.RoleTst;
import com.clinic.vaxschedular.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    User findByRole(RoleTst roleTst);

}
