package cn.qlu.yhy.utils;

import java.util.HashMap;
import java.util.Map;

import cn.qlu.yhy.model.User;

public class AppContext {

    public static ThreadLocal<AppContext> appContextMap = new ThreadLocal<AppContext>();

    private Map<String, Object> objects = new HashMap<String, Object>();

    private AppContext() {}

    public Map<String, Object> getObjects() {
        return objects;
    }

    public void setObjects(Map<String, Object> objects) {
        if (objects == null) {
            objects = new HashMap<String, Object>();
        }

        this.objects = objects;
    }

    public static AppContext getAppContext() {
        AppContext appContext =appContextMap.get();

        if (appContext == null) {
            appContext = new AppContext();

            appContextMap.set(appContext);
        }

        return appContext;
    }

    public User getUser() {
        return (User) objects.get("USER");
    }

    public String getUserName() {
        User user = (User) objects.get("USER");
        if (user != null) {
            return user.getUsername();
        }

        return null;
    }

    public void addObject(String key, Object value) {
        objects.put(key, value);
    }

    public Object getObject(String key) {
        return objects.get(key);
    }

    public void removeObject(String key) {
        objects.remove(key);
    }

    public String getContextPath() {
        return (String) objects.get("contextPath");
    }

    public void clear() {
        objects.clear();
    }
}