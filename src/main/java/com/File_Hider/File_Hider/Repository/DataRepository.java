package com.File_Hider.File_Hider.Repository;

import com.File_Hider.File_Hider.Models.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data,Long> {
    List<Data> findByemail(String email);
}
