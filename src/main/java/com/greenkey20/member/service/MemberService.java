package com.greenkey20.member.service;

import com.greenkey20.member.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 2022.12.1(목) 6h5 클래스만 생성 -> 2022.12.12(월) 2h 이어서 구현
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {

    }



}
