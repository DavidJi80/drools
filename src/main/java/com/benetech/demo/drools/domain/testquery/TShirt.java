package com.benetech.demo.drools.domain.testquery;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 季舟
 * @copyright 上海智赢健康科技有限公司
 * @since 2021/2/1 13:35
 */
@Data
@AllArgsConstructor
public class TShirt {
    private String mainColor;
    private String secondColor ;
    private double manufactureCost;
}
