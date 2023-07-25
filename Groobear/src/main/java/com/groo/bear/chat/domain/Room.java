package com.groo.bear.chat.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomNo;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Users user;

    @Column(name = "room_time", nullable = false)
    private Date roomTime;

    @Column(name = "room_name", nullable = false)
    private String roomName;

}