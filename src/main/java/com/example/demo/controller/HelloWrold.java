package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.response.HelloWroldRes;
import com.example.demo.service.Div;
import com.example.demo.service.DivTest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yyhu3
 * @Date 2018-07-03 17:01
 */
@RestController
    public class HelloWrold {
    @Autowired
    Div div;
    Logger logger = Logger.getLogger(HelloWrold.class);

    @GetMapping("/yanxuan/test/add")
    public int add(int add1,int  add2)
    {
       int sum =  add1+add2;
        logger.info("第26行");
       return sum;
    }


    @GetMapping("/yanxuan/test")
    public String DivResult(int divisor,int  dividend,String errorMsg)
    {
        logger.info("现在是第34行啦~~~");
        HelloWroldRes helloWroldRes = new HelloWroldRes();
        switch (divisor){
            case 1:
                helloWroldRes.setErrorMsg("input 1");
                break;
            case 2:
                helloWroldRes.setErrorMsg("input 2");
                break;
            case 3:
                helloWroldRes.setErrorMsg("input 3");
                break;
            default:
                helloWroldRes.setErrorMsg("default"+errorMsg);
        }
        try{
            if (divisor==99999999) {
                DivTest divTest = new DivTest();
                int result = div.divResult(divisor,dividend);
                logger.info("现在是第44行啦~~~");
                logger.info("result="+result);
                helloWroldRes.setMultiplication(divTest.divResult(divisor,dividend));
                helloWroldRes.setData(result);
                helloWroldRes.setCode(200);
            }
            else
            {
                helloWroldRes.setCode(400);
            }
        }catch (Exception e)
        {
            logger.error("发生未知异常");
        }
        logger.info("返回包为:"+JSON.toJSONString(helloWroldRes));
        return JSON.toJSONString(helloWroldRes);
    }
}
