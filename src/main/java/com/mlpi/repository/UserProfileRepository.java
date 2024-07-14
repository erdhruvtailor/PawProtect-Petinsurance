package com.mlpi.repository;

import com.mlpi.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

    @Query("FROM UserProfile up where up.user.id = :id")
    public UserProfile getUserProfileByUser(Long id);

}
