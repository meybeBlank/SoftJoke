package com.fengz.softjoke.http;

import com.google.gson.annotations.SerializedName;

/**
 * 创建时间：2018/11/5
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：网络请求响应壳
 */
public class ResponseShuck<T> {

    /**
     * code : 0 状态码
     * data : {} 具体的数据
     * msg : "" 待提示错误信息
     */
    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private T content;

    @SerializedName("msg")
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
