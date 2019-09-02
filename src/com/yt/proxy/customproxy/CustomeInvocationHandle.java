package com.yt.proxy.customproxy;

import java.lang.reflect.Method;

public interface CustomeInvocationHandle {
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
