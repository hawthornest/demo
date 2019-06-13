package com.example.demo.service;

import com.example.demo.controller.HelloWrold;
import org.apache.log4j.Logger;

/**
 * @Author yyhu3
 * @Date 2018-12-28 10:25
 */
public class DivTest extends Div{
    Logger logger = Logger.getLogger(DivTest.class);
    @Override
    public int divResult(int divisor,int  dividend)
    {
        logger.info("进入方法DivTest的divResult啦");
        int result;
        if (dividend==0)
        {
            result = 10000000;
            System.out.println("result="+result);
        }
        else
        {
            result = divisor*dividend;
        }
        return result;
    }
}
