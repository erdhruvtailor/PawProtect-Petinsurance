package com.mlpi.repository;

import com.mlpi.model.Coverage;
import com.mlpi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CoverageRepository extends JpaRepository<Coverage,Long> {

    @Query("FROM Coverage coverage where coverage.user.id = :id")
    public List<Coverage> getCoverageByUser(Long id);

    public Coverage getCoverageById(Long id);
}
