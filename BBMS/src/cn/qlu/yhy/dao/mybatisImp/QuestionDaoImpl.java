package com.augmentum.dao.impl.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.augmentum.dao.QuestionDao;
import com.augmentum.model.Page;
import com.augmentum.model.Question;

public class QuestionDaoImpl implements QuestionDao {
    @Autowired
    private SqlSessionTemplate sst;

    private static final String CLASSNAME = Question.class.getName();

    @Override
    public int createQuestion(Question question) {
        int succ1 = sst.insert(CLASSNAME + ".createQuestion", question);

        int id = question.getId();

        String qQuestionId = "Q" + String.format("%05d", id);

        question.setQuestionId(qQuestionId);

        int succ2 = updateQuestionId(qQuestionId, id);



        return succ1 + succ2;
    }

    @Override
    public int deleteQuestion(String questionId) {
        return sst.delete(CLASSNAME + ".deleteQuestion", questionId);
    }

    @Override
    public int updateQuestion(Question question) {
        return sst.update(CLASSNAME + ".updateQuestion", question);
    }

    @Override
    public List<Question> findQuestionsByName(String questionName, Page<Question> page) {

        questionName = "%" + questionName + "%";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("questionName", questionName);
        params.put("page", page);
        List<Question> list = sst.selectList(CLASSNAME + ".findQuestionsByName", params);

        page.setTotalRecord(getTotalPageCountByName(questionName));

        return list;
    }

    @Override
    public List<Question> findQuestions(Page<Question> page) {

        page.setTotalRecord(getTotalPageCount());

        return sst.selectList(CLASSNAME + ".findQuestions", page);
    }

    @Override
    public Question getQuestionById(String questionId) {

        List<Question> list = sst.selectList(CLASSNAME + ".getQuestionById", questionId);

        return list.get(0);
    }

    @Override
    public String getQuestionByName(String questionName) {
        if (questionName == null || questionName.trim().isEmpty()) {
            return null;
        }

        List<Question> list = sst.selectOne(CLASSNAME + ".getQuestionByName", questionName);

        return list.get(0).getId()+"";
    }

    private int getTotalPageCount() {
        return sst.selectOne(CLASSNAME + ".getTotalPageCount");
    }

    private int getTotalPageCountByName(String questionName) {
        return sst.selectOne(CLASSNAME + ".getTotalPageCountByName", questionName);
    }

    private int updateQuestionId(String questionId, int id) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("questionId", questionId);
        params.put("id", id);

        int succ = sst.update(CLASSNAME + ".updateQuestionId", params);

        return succ;
    }
}