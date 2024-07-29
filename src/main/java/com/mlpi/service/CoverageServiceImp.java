package com.mlpi.service;

import com.mlpi.dto.CoverageDto;
import com.mlpi.model.Coverage;
import com.mlpi.repository.CoverageRepository;
import com.mlpi.service.mapper.CoverageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoverageServiceImp implements CoverageService {

    @Autowired
    CoverageRepository coverageRepository;

    @Autowired
    CoverageMapper coverageMapper;

    public Coverage saveCoverage(Coverage coverage) {
        return coverageRepository.save(coverage);
    }

    public List<Coverage> getCoverageByUser(Long id) {
        return coverageRepository.getCoverageByUser(id);
    }

    public Coverage getCoverageById(Long id) {
        return coverageRepository.getCoverageById(id);
    }

    public long getCoveragesCountByUser(Long userId) {
        return coverageRepository.countCoveragesByUserId(userId);
    }

    public Double sumUserWiseGrandTotal(Long userId) {
        return coverageRepository.sumUserWiseGrandTotal(userId);
    }

    public List<CoverageDto> getCoverageDTOs(long id) {
        List<Coverage> coverages = coverageRepository.getCoverageByUser(id);
        return coverages.stream()
                .map(CoverageMapper::toDTO)
                .collect(Collectors.toList());
    }
}
