package com.augmentum.util;

import java.lang.reflect.Method;

import javax.servlet.http.HttpSession;

import com.augmentum.common.AppContext;

public class SessionUtil {

    public static HttpSession getSessionInThread() {
        return (HttpSession) AppContext.getAppContext().getObject("session");
    }

    //Add in setAttribute method
    public static void addSession(String key, Object value) {
        HttpSession session = getSessionInThread();

        if (session == null) {
            return;
        }

        try {
            Class<?>[] params = new Class[2];
            params[0] = String.class;
            params[1] = Object.class;

            Method method = session.getClass().getMethod("setAttribute", params);

            Object[] objects = new Object[2];
            objects[0] = key;
            objects[1] = value;


            method.invoke(session, objects);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //GetAttribute
    public static Object getSession(String key) {
        HttpSession session = getSessionInThread();

        if (session == null) {
            return null;
        }

        Object object = null;
        try {
            Method method = session.getClass().getMethod("getAttribute", String.class);

            object = method.invoke(session, key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return object;
    }

    //RemoveSession
    public static void removeSession(String key) {
        HttpSession session = getSessionInThread();

        if (session == null) {
            return;
        }

        try {
            Method method = session.getClass().getMethod("removeAttribute", String.class);

            method.invoke(session, key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //Invalidate
    public static void invalidate() {
        Object session = getSessionInThread();
        if (session == null) {
            return;
        }
        try {
            Method method = session.getClass().getMethod("invalidate");
            method.invoke(session);
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException(e);
        }
    }
}