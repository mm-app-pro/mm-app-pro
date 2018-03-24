package com.xjy.util;

public class RespBody {

    public RespBody() {

    }

    public RespBody(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    Integer code;
    String message;
    Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCode() {
        return code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "RespBody [code=" + code + ", message=" + message + ", roleId=" + roleId + "]";
    }

}
