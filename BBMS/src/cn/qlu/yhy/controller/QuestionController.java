package com.augmentum.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.augmentum.model.Page;
import com.augmentum.model.Question;
import com.augmentum.service.QuestionService;
import com.augmentum.util.StringUtil;

@Controller
public class QuestionController extends BaseController {

    @Autowired
    private QuestionService questionService;

    @ResponseBody
    @RequestMapping(value = "goCreatePage", method = RequestMethod.POST)
    public ModelAndView goCreatePage() throws ServletException, IOException {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("question/create_question");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView create(String question, String answerA, String answerB, String answerC, String answerD,
            String answerTrue) throws ServletException, IOException {

        Question questionC = new Question(question, answerA, answerB, answerC, answerD, answerTrue);

        boolean flag = questionService.createQuestion(questionC);

        ModelAndView modelAndView = goCreatePage();
        modelAndView.addObject("questionId", questionC.getQuestionId());
        modelAndView.addObject("showConfirm", flag);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Page<Question> delete(String delete) throws ServletException, IOException {

        if (delete.startsWith(",")) {
            delete = delete.substring(1);
        }

        String[] strqid = delete.split(",");

        Map<String, Boolean> deleteMessage = new HashMap<String, Boolean>();

        for (String str : strqid) {
            boolean flag = questionService.deleteQuestion(str.trim());

            deleteMessage.put(str.trim(), flag);
        }

        Page<Question> page = new Page<Question>();

        page.setDeleMess(deleteMessage);

        page.setTfMessage(true);

        return page;
    }

    @RequestMapping(value = "goEditPage", method = RequestMethod.POST)
    public ModelAndView goEditPage(String editQuestionId) throws ServletException, IOException {

        Question question = questionService.getQuestionById(editQuestionId);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("question", question);
        modelAndView.setViewName("question/edit_question");

        return modelAndView;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ModelAndView edit( String currentPage,
                              String pageSize,
                              String editQuestionId,
                              String question,
                              String answerA,
                              String answerB,
                              String answerC,
                              String answerD,
                              String answerTrue
                            ) throws ServletException, IOException {

        Question questionE = new Question(editQuestionId, question, answerA, answerB, answerC, answerD, answerTrue);

        boolean flag = questionService.updateQuestion(questionE);

        ModelAndView modelAndView = goEditPage(editQuestionId);
        modelAndView.addObject("showConfirm", flag);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "showAll", method = RequestMethod.POST)
    public Page<Question> showAll(String currentPage, String pageSize, String sequence) throws ServletException,
            IOException {

        Page<Question> page = null;

        if (StringUtil.isEmpty(sequence)) {
            if (StringUtil.isEmpty(currentPage) && StringUtil.isEmpty(pageSize)) {
                page = new Page<Question>();
            } else {
                page = new Page<Question>(pageSize, currentPage);
            }
        } else {
            if (StringUtil.isEmpty(currentPage) && StringUtil.isEmpty(pageSize)) {
                page = new Page<Question>(sequence);
            } else {
                page = new Page<Question>(pageSize, currentPage, sequence);
            }
        }

        page.setList(questionService.findQuestions(page));

        return page;
    }

    @ResponseBody
    @RequestMapping(value = "showAllByName", method = RequestMethod.POST)
    public Page<Question> showAllByName(String questionName, String currentPage, String pageSize)
            throws ServletException, IOException {

        if (!StringUtil.isEmpty(questionName)) {

            if (StringUtil.isEmpty(pageSize)) {
                pageSize = "10";
            }

            Page<Question> page = null;

            if (StringUtil.isEmpty(currentPage)) {
                page = new Page<Question>();
            } else {
                page = new Page<Question>(pageSize, currentPage);
            }

            List<Question> questionList = questionService.findQuestionsByName(questionName, page);

            page.setMessage(questionName);

            page.setList(questionList);

            return page;
        } else {

            Page<Question> page = showAll(null, null, null);
            return page;
        }
    }

    @ResponseBody
    @RequestMapping(value = "showByQuestionId", method = RequestMethod.POST)
    public ModelAndView showByQuestionId(String showByQuestionId) throws ServletException, IOException {

        Question question = questionService.getQuestionById(showByQuestionId);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("question", question);
        modelAndView.setViewName("question/question_detail");
        return modelAndView;
    }
}