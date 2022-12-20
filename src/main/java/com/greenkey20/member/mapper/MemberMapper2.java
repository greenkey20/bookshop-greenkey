package com.greenkey20.member.mapper;

import com.greenkey20.member.dto.MemberDto;
import com.greenkey20.member.entity.Member;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

// 2022.12.20(화) 11h40 todoapp 구현 예시 보고 참고해서 작성
@Component
public class MemberMapper2 {
    public Member memberRequestDtoToMember(MemberDto.Request requestBody) {
        if (requestBody == null) {
            return null;
        }

        if (ObjectUtils.isEmpty(requestBody.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (ObjectUtils.isEmpty(requestBody.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }





    }
}
