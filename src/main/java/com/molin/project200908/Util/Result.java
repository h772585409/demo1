package com.molin.project200908.Util;

import io.netty.util.concurrent.SucceededFuture;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 封装返回结果
 */
@ApiModel(description = "封装返回结果")
public class Result implements Serializable{
    @ApiModelProperty(name = "flag",value = "执行结果，true为执行成功 false为执行失败")
    private boolean flag;//执行结果，true为执行成功 false为执行失败
    @ApiModelProperty(name = "message",value = "返回结果信息，主要用于页面提示信息")
    private String message;//返回结果信息，主要用于页面提示信息
    @ApiModelProperty(name = "data",value = "返回数据")
    private Object data;//返回数据

    public Result(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }

    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result succeed(Object object){
        return new Result(true,"成功",object);
    }
    public static Result fail(String message){
        return new Result(false,message);
    }
    public static Result fail(){
        return new Result(false,"失败");
    }
}
