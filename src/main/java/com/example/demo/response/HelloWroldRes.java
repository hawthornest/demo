package com.example.demo.response;

import com.example.demo.service.Div;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author yyhu3
 * @Date 2018-07-03 17:02
 */
@ResponseBody
public class HelloWroldRes {


    private int code;
    private int data;
    private int multiplication;

    public int getMultiplication() {
        return multiplication;
    }

    public void setMultiplication(int multiplication) {
        this.multiplication = multiplication;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    private String errorMsg;



}
