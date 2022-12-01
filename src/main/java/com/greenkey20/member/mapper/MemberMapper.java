package com.greenkey20.member.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

// 2022.12.1(목) 6h5
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
}
