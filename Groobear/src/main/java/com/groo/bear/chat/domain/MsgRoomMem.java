package com.groo.bear.chat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "msg_room_mem")
@Data
public class MsgRoomMem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memNo;
    //Entity 어노테이션을 사용하면, 이 클래스는 JPA에서 관리하는 엔티티 클래스가 된다.
    // JPA에서는 엔티티 클래스가 반드시 기본키(Primary Key)를 가지고 있어야 한다.
    // 기본키는 해당 테이블의 각 레코드를 고유하게 식별하는 역할을 한다.
    //원래 테이블에는 기본키가 없었지만, JPA를 사용하려면 기본키가 필요하기 때문에 
    // memNo 필드를 새롭게 추가하고 이를 기본키로 설정함.

    @ManyToOne
    @JoinColumn(name = "room_no", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Users user;

    @Column(name = "msg_check", nullable = false)
    private char msgCheck;

}
