package org.example.Repository;

import org.example.DTOs.UserBasicDTO;
import org.example.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT new org.example.DTOs.UserBasicDTO(u.id, u.login, u.email) FROM User u")
    List<UserBasicDTO> findAllUserBasics();

}
