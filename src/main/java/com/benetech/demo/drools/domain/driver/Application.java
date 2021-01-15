package com.benetech.demo.drools.domain.driver;

import java.util.Date;
import lombok.Data;

@Data
public class Application {
    private Date dateApplied;
    private boolean valid;
}
