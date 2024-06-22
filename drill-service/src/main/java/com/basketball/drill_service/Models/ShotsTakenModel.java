package com.basketball.drill_service.Models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ShotsTakenModel {
    private int threePointLeftCorner;
    private int threePointRightCorner;
    private int threePointLeftWing;
    private int threePointRightWing;
    private int threePointMiddle;
    private int twoPointLeftCorner;
    private int twoPointRightCorner;
    private int twoPointLeftWing;
    private int twoPointRightWing;
    private int twoPointMiddle;
}
