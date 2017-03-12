package com.augmentum.util;

import com.augmentum.common.AppContext;

public class PathUtil {
    public static String getFullPath(String path) {
        if (path == null) {
            path = "";
        }
//        String urlPrefix = Constants.APP_URL_PREFIX;
//        if (!StringUtil.isEmpty(urlPrefix)) {
//            urlPrefix += "/";
//        }

//        return AppContext.getAppContext().getContextPath() + "/" + urlPrefix  + path;
        return AppContext.getAppContext().getContextPath() + "/augmentum" + path;
    }
}
