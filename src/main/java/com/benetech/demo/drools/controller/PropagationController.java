package com.benetech.demo.drools.controller;

import com.benetech.demo.drools.service.PropagationModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 季舟
 * @copyright 上海智赢健康科技有限公司
 * @since 2021/2/2 10:39
 */
@RestController
@RequestMapping("/propagation")
public class PropagationController {
    @Autowired
    private PropagationModeService propagationModeService;

    @GetMapping("/test_lazy")
    public String testQuery(){
        propagationModeService.testLazyMode();
        return "Lazy Mode";
    }
}
