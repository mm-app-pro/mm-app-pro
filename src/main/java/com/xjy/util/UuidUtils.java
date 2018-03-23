package com.xjy.util;

import java.util.UUID;

public final class UuidUtils {

    public static String create() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
