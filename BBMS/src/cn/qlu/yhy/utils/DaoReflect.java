package cn.qlu.yhy.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DaoReflect {

    public static <T> T setDaoValue(Class<T> clazz, ResultSet rs) {
        T t = null;

        try {
            t = clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                String fieldName = field.getName();
                ResultSetMetaData resultSetMetaData;
                resultSetMetaData = rs.getMetaData();

                for (int index = 1 ; index <= resultSetMetaData.getColumnCount(); index++) {
                    String columnName;
                    columnName = resultSetMetaData.getColumnName(index);
                    String newColumnName = columnName.replace("_", "");
                    if (newColumnName.equalsIgnoreCase(fieldName)) {
                        Object value = rs.getObject(columnName);
                        field.set(t, value);
                    }
                }
            }
        } catch (InstantiationException | SQLException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}