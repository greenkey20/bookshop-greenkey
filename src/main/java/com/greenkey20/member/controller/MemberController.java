package com.greenkey20.member.controller;

import com.greenkey20.member.dto.MemberDto;
import com.greenkey20.member.entity.Member;
import com.greenkey20.member.mapper.MemberMapper;
import com.greenkey20.member.service.MemberService;
import com.greenkey20.sticker.entity.Sticker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

// 2022.12.1(목) 6h
@RestController
@RequestMapping("/v1/members")
@Validated
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    // 2022.12.9(금) 23h30
    // 회원 가입(create)
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
        Member member = memberMapper.memberPostDtoToMember(requestBody);
        member.setSticker(new Sticker());

        Member createdMember = memberService.createMember(member);

        return new ResponseEntity<>(new SingleResponseDto<>(memberMapper.memberToMemberResponse(createdMember)), HttpStatus.CREATED);
    }

    // 2022.12.11(일) 23h
    // 회원 1명 조회(read)
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId) {
        Member member = memberService.findMember(memberId);

        return new ResponseEntity<>(new SingleResponseDto<>(memberMapper.memberToMemberResponse(member)), HttpStatus.OK);
    }

    // 회원 여러 명 조회(read)
    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page, @Positive @RequestParam int size) {
        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
        List<Member> members = pageMembers.getContent();

        return new ResponseEntity<>(new MultipleResponseDto<>(memberMapper.membersToMemberResponses(members)), HttpStatus.OK);
    }

    // 회원 1명 정보 수정(update)
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId, @Valid @RequestBody MemberDto.Patch requestBody) {
        requestBody.setMemberId(memberId);

        Member member = memberService.updateMember(memberMapper.memberPatchDtoToMember(requestBody));

        return new ResponseEntity<>(new SingleResponseDto<>(memberMapper.memberToMemberResponse(member)), HttpStatus.OK);
    }

    // 회원 탈퇴(delete)
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
