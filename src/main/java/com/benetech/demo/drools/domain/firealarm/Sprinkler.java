package com.benetech.demo.drools.domain.firealarm;

import lombok.Data;

@Data
public class Sprinkler {
    private Room room;
    private boolean on;

    public Sprinkler(Room room) {
        this.room = room;
    }
}
