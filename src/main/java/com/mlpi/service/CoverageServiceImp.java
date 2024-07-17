package com.mlpi.service;

import com.mlpi.model.Coverage;
import com.mlpi.model.User;
import com.mlpi.repository.CoverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CoverageServiceImp implements CoverageService {

    @Autowired
    CoverageRepository coverageRepository;

    public Coverage saveCoverage(Coverage coverage) {
        return coverageRepository.save(coverage);
    }

    public List<Coverage> getCoverageByUser(Long id) {
        return coverageRepository.getCoverageByUser(id);
    }

    public Coverage getCoverageById(Long id) {
        return coverageRepository.getCoverageById(id);
    }

}
