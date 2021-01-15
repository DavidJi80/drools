package com.benetech.demo.drools.domain.firealarm;

import lombok.Data;

@Data
public class Room {
    private String name;

    public Room(String name) {
        this.name = name;
    }
}
