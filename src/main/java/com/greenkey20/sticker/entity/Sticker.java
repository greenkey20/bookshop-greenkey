package com.greenkey20.sticker.entity;

import com.greenkey20.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;

// 2022.12.20(화) 17h5 구현
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Sticker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stickerId;

    @Column(nullable = false)
    private int stickerCount;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        if (member.getSticker() != this) {
            member.setSticker(this);
        }
    }
}
