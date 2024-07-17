package com.mlpi.service;

import com.mlpi.model.Coverage;

import java.util.List;

public interface CoverageService {

    public Coverage saveCoverage(Coverage coverage);

    public List<Coverage> getCoverageByUser(Long id);

    public Coverage getCoverageById(Long id);

}
