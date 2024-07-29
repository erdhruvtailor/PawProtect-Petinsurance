package com.mlpi.service.mapper;

import com.mlpi.dto.CoverageDto;
import com.mlpi.model.Coverage;
import org.springframework.stereotype.Service;

@Service
public class CoverageMapper {

    public static CoverageDto toDTO(Coverage coverage) {
        CoverageDto dto = new CoverageDto();
        dto.setAnnualLimit(coverage.getAnnualLimit());
        dto.setReimbursementRate(coverage.getReimbursementRate());
        dto.setAnnualDeductible(coverage.getAnnualDeductible());
        return dto;
    }

}
