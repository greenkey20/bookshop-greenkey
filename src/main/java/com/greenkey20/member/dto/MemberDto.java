package com.greenkey20.member.dto;

import com.greenkey20.member.entity.Member;
import com.greenkey20.sticker.entity.Sticker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

// 2022.12.12(월) 1h45
public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        @Email
        private String email;

        @NotBlank(message = "이름은 공백이 아니어야 합니다")
        private String name;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$")
        private String phone;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long memberId;

        @NotBlank(message = "이름은 공백이 아니어야 합니다")
        private String name;
        private String phone;
        private Member.MemberStatus memberStatus;

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String email;
        private String name;
        private String phone;
        private Member.MemberStatus memberStatus;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long memberId;
        private String email;
        private String name;
        private String phone;
        private Member.MemberStatus memberStatus;
        private Sticker sticker;

        public String getmemberStatus() {
            return memberStatus.getStatus();
        }

        public int getSticker() {
            return sticker.getStickerCount();
        }
    }
}
