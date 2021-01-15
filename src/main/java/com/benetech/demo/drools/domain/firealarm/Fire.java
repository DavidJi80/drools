package com.benetech.demo.drools.domain.firealarm;

import lombok.Data;

@Data
public class Fire {
    private Room room;

    public Fire(Room room) {
        this.room = room;
    }
}
