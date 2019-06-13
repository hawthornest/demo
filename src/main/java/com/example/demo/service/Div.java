package com.example.demo.service;

import com.example.demo.controller.HelloWrold;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @Author yyhu3
 * @Date 2018-07-04 16:01
 */

@Service
public class Div {
    Logger logger = Logger.getLogger(Div.class);
    public int divResult(int divisor,int  dividend)
    {
        int result;
        if (dividend==0)
        {
            logger.info("进入除数=0逻辑");
            result = 99999999;
        }
        else
        {
            logger.info("进入正常的Div下的divResult的处理方法啦");
            result = divisor/dividend;
        }
        return result;
    }
}
