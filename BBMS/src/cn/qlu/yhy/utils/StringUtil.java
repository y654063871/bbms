package cn.qlu.yhy.utils;

import org.hamcrest.Matcher;

public class StringUtil {

    public static boolean isEmpty(String str) {

        if (str == null || str.trim().equals("")) {
            return true;
        }

        return false;
    }

    public static boolean regexTest(String regex, String text) {
        Pattern patternQuestion = Pattern.compile(regex);

        Matcher matcherQuestion = patternQuestion.matcher(text);

        if (!matcherQuestion.find()) {
            return  false;
        }

        return true;
    }

    public static String toChangeQuestionName(String questionName) {

        String[] fbsArr = { "<", ">", "_", "%", "\"" };

        for (String key : fbsArr) {
            if (questionName.contains(key)) {
                questionName = questionName.replace(key, "\\" + key);
            }
        }

        return questionName;
    }

}
