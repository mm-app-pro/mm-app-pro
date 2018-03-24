package com.xjy.enums;

public enum OrderStatusEnum {
    WAITECHECK("待审核"), ISCHECK("已审核"), VALID("执行中"), INVALID("无效"), FEEDBACK("待反馈"), FINISH("完成");
    private String name;

    private OrderStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
