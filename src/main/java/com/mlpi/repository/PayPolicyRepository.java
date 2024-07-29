package com.mlpi.repository;

import com.mlpi.model.Coverage;
import com.mlpi.model.PayPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayPolicyRepository extends JpaRepository<PayPolicy,Long> {

    @Query("FROM PayPolicy payPolicy where payPolicy.user.id = :id ORDER BY payPolicy.id DESC")
    public List<PayPolicy> getPayByUser(Long id);

    public PayPolicy getPayPoliciesById(Long id);

    @Query("SELECT COUNT(PayPolicy) FROM PayPolicy payPolicy WHERE payPolicy.user.id = :userId")
    long countPayTimesByUserId(Long userId);

    @Query("SELECT SUM(payPolicy.amount) FROM PayPolicy payPolicy WHERE payPolicy.user.id = :userId")
    Double sumUserWisePay(Long userId);

}
