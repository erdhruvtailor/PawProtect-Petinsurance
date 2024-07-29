package com.mlpi.service;

import com.mlpi.model.PayPolicy;
import com.mlpi.repository.PayPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayPolicyServiceImp implements PayPolicyService {

    @Autowired
    PayPolicyRepository payPolicyRepository;

    public PayPolicy savePayPolicy(PayPolicy payPolicy) {
        return payPolicyRepository.save(payPolicy);
    }

    public List<PayPolicy> getPayByUser(Long id) {
        return payPolicyRepository.getPayByUser(id);
    }

    public PayPolicy getPayPolicyById(Long id) {
        return payPolicyRepository.getPayPoliciesById(id);
    }

    public long getTotalPayTimesCountByUser(Long userId) {
        return payPolicyRepository.countPayTimesByUserId(userId);
    }

    public Double sumUserWisePay(Long userId) {
        return payPolicyRepository.sumUserWisePay(userId);
    }

}
