package com.File_Hider.File_Hider.Repository;

import com.File_Hider.File_Hider.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
