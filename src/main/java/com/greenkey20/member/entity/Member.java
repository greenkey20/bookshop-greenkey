package com.greenkey20.member.entity;

import com.greenkey20.order.entity.Order;
import com.greenkey20.sticker.entity.Sticker;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.domain.Auditable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 2022.12.1(목) 새벽 클래스만 생성 -> 2022.12.20(화) 15h30 구현
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 13, nullable = false, unique = true)
    private String phone;

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("MEMBER_ACTIVE") // 16h 나의 질문 = 이 annotation을 MemberStatus enum 타입에 대해 써도 되는 것인가?
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>;

    @OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Sticker sticker;

    public void setOrder(Order order) {
        orders.add(order);
        if (order.getMember() != this) {
            order.setMember(this);
        }
    }

    public void setSticker(Sticker sticker) {
        this.sticker = sticker;
        if (sticker.getMember() != this) {
            sticker.setMember(this);
        }
    }

    public enum MemberStatus {
        MEMBER_ACTIVE("활동 중"),
        MEMBER_SLEEP("휴면 상태"),
        MEMBER_QUIT("탈퇴 상태");

        @Getter
        private String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }
}
