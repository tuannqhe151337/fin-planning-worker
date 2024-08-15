package com.example.planning_worker.utils.mapper.annual;

import com.example.planning_worker.entity.AnnualReport;
import com.example.planning_worker.entity.Report;
import com.example.planning_worker.repository.result.AnnualReportResult;
import com.example.planning_worker.repository.result.ReportResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnnualReportMapper {
    @Mapping(expression = "java(annualReportResult.getYear())",target = "year")
    @Mapping(expression = "java(annualReportResult.getTotalTerm())",target = "totalTerm")
    @Mapping(expression = "java(annualReportResult.getTotalDepartment())",target = "totalDepartment")
    AnnualReport mapToAnnualReportMapping(AnnualReportResult annualReportResult);

    @Mapping(expression = "java(reportResult.getDepartmentId())",target = "department.id")
    @Mapping(expression = "java(reportResult.getTotalExpense())",target = "totalExpense")
    @Mapping(expression = "java(reportResult.getBiggestExpense())",target = "biggestExpenditure")
    @Mapping(expression = "java(reportResult.getCostTypeId())",target = "costType.id")
    Report mapToReportMapping(ReportResult reportResult);
}