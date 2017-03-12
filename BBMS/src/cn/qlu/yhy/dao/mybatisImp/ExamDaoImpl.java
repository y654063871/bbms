package cn.qlu.yhy.dao.mybatisImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class ExamDaoImpl implements ExamDao {
    @Autowired
    private SqlSessionTemplate sst;

    private static final String CLASS_NAME = Exam.class.getName();

    @Override
    public int createExam(Exam exam) {
        return sst.insert(CLASS_NAME+ ".createExam", exam);
    }

    public int updateExamId(String id, String examId) {
        Map<String, String> params = new HashMap<String, String>();

        System.out.println("id " + id + " EXAMId " + examId);

        params.put("examId", examId);
        params.put("id", id);

        return sst.update(CLASS_NAME + ".updateExamId", params);
    }

    public List<Question> findRandomQuestion(int quantity) {
        return sst.selectList(CLASS_NAME + ".findRandomQuestion", quantity);
    }

    public int insertQuestion2Exam(int examId, int questionId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("examId", examId);
        params.put("questionId", questionId);

        return sst.insert(CLASS_NAME + ".insertQuestion2Exam", params);
    }

    public int insertQuestion2CopyQuestion(Question question) {
        return sst.insert(CLASS_NAME + ".insertQuestion2CopyQuestion", question);
    }

    @Override
    public int deleteExam(String examId) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateExam(Exam exam) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Exam getExamByName(String examName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Exam getExamById(String examId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Exam> findExams() {
        return sst.selectList(CLASS_NAME + ".findExams");
    }

    @Override
    public List<Exam> findExamsByName() {
        // TODO Auto-generated method stub
        return null;
    }

    //    @Override
    //    public User getUserByName(String userName) {
    //        return sst.selectOne(CLASS_NAME + ".getUserByName", userName);
    //    }


}
