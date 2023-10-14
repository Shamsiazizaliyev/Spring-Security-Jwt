package com.example.springsecurityjwt.repository;




import com.example.springsecurityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepostory extends JpaRepository<User,Integer> {



    Optional<User> findByUsername(String username);

}
