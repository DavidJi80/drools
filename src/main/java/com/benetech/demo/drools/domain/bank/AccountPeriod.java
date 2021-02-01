package com.benetech.demo.drools.domain.bank;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author 季舟
 * @copyright 上海智赢健康科技有限公司
 * @since 2021/2/1 17:25
 */
@Data
@AllArgsConstructor
public class AccountPeriod {

    private Date start;
    private Date end;
}
