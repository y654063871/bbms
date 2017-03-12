package com.augmentum.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.augmentum.model.Exam;
import com.augmentum.model.Page;
import com.augmentum.service.ExamService;
import com.augmentum.util.StringUtil;

@Controller
public class ExamController extends BaseController {

    @Autowired
    private ExamService examService;

    //

    @RequestMapping(value = "showAllExamGet", method = RequestMethod.GET)
    public String goShowAllPage() {
        return "/exam/exam_management";
    }

    @ResponseBody
    @RequestMapping(value = "showAllExam", method = RequestMethod.POST)
    public Page<Exam> showAll(String currentPage, String pageSize, String sequence) throws ServletException,
            IOException {

        Page<Exam> page = null;

        if (StringUtil.isEmpty(sequence)) {
            if (StringUtil.isEmpty(currentPage) && StringUtil.isEmpty(pageSize)) {
                page = new Page<Exam>();
            } else {
                page = new Page<Exam>(pageSize, currentPage);
            }
        } else {
            if (StringUtil.isEmpty(currentPage) && StringUtil.isEmpty(pageSize)) {
                page = new Page<Exam>(sequence);
            } else {
                page = new Page<Exam>(pageSize, currentPage, sequence);
            }
        }

        List<Exam> examList = examService.findExams(page);

        page.setList(examList);

        return page;
    }


    @RequestMapping(value = "goCreateExamPage", method = RequestMethod.POST)
    public ModelAndView goCreateExamPage() throws ServletException, IOException {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/exam/create_exam");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "createExam", method = RequestMethod.POST)
    public Page<Exam> create(Exam exam, String date, String datetime) throws ServletException, IOException {

        String time = date + " " + datetime;
        System.out.println(time);

        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        Date dateFormate = null;
        try {
            dateFormate = formate.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        exam.setEffectiveTime(dateFormate);

        System.out.println("examNmae: " + exam.getExamName() + " description: " + exam.getDescription() + " dateformate: " + exam.getEffectiveTime() + " questionQuantity: " + exam.getQuestionQuantity() + " single: " + exam.getSingleQuestionScore() + " total " + exam.getTotalScore() + " pass " + exam.getPassCriteria());

        boolean flag = examService.createExam(exam);

        Map<String, Object> objects = new HashMap<String, Object>();
        objects.put("examId", exam.getExamId());
        objects.put("createMessage", flag);

        Page<Exam> page = new Page<Exam>();
        page.setTfMessage(true);

        return page;
    }
    //
    //
    // @RequestMapping(value = "delete", method = RequestMethod.POST)
    // public ModelAndView delete(String delete) throws ServletException,
    // IOException {
    //
    // String[] strqid = delete.split(",");
    //
    // Map<String, Boolean> deleteMessage = new HashMap<String, Boolean>();
    //
    // for (String str : strqid) {
    // boolean flag = questionService.deleteQuestion(str);
    // deleteMessage.put(str, flag);
    // }
    //
    // ModelAndView modelAndView = showAll(null, null, null);
    //
    // modelAndView.addObject("deleteMessage", deleteMessage);
    //
    // return modelAndView;
    // }
    //
    //
    // @RequestMapping(value = "goEditPage", method = RequestMethod.POST)
    // public ModelAndView goEditPage(String editQuestionId) throws
    // ServletException, IOException {
    //
    // Question question = questionService.getQuestionById(editQuestionId);
    //
    // ModelAndView modelAndView = new ModelAndView();
    //
    // modelAndView.addObject("question", question);
    // modelAndView.setViewName("edit_question");
    //
    // return modelAndView;
    // }
    //
    //
    // @RequestMapping(value = "edit", method = RequestMethod.POST)
    // public ModelAndView edit( String currentPage,
    // String pageSize,
    // String editQuestionId,
    // String question,
    // String answerA,
    // String answerB,
    // String answerC,
    // String answerD,
    // String answerTrue
    // ) throws ServletException, IOException {
    // Page page = null;
    //
    // if (StringUtil.isEmpty(currentPage) && StringUtil.isEmpty(pageSize)) {
    // page = new Page();
    // } else {
    // page = new Page(pageSize, currentPage);
    // }
    //
    // Question questionE = new Question(editQuestionId, question, answerA,
    // answerB, answerC, answerD, answerTrue);
    //
    // questionService.updateQuestion(questionE);
    //
    // ModelAndView modelAndView = goEditPage(editQuestionId);
    // modelAndView.addObject("showConfirm", "true");
    //
    // return modelAndView;
    // }
    //
    //

//    @ResponseBody
//    @RequestMapping(value = "showAll", method = RequestMethod.POST)
//    public Page<Question> showAll(String currentPage, String pageSize, String sequence) throws ServletException,
//            IOException {
//
//        Page<Question> page = null;
//
//        if (StringUtil.isEmpty(sequence)) {
//            if (StringUtil.isEmpty(currentPage) && StringUtil.isEmpty(pageSize)) {
//                page = new Page<Question>();
//            } else {
//                page = new Page<Question>(pageSize, currentPage);
//            }
//        } else {
//            if (StringUtil.isEmpty(currentPage) && StringUtil.isEmpty(pageSize)) {
//                page = new Page<Question>(sequence);
//            } else {
//                page = new Page<Question>(pageSize, currentPage, sequence);
//            }
//        }
//
//        page.setList(questionService.findQuestions(page));
//
//        return page;
//    }




    //
    // @RequestMapping(value = "showAllByName", method = RequestMethod.POST)
    // public ModelAndView showAllByName(String questionName, String
    // currentPage) throws ServletException, IOException {
    //
    // if (!StringUtil.isEmpty(questionName)) {
    // ModelAndView modelAndView = new ModelAndView();
    //
    // modelAndView.addObject("searchName", questionName);
    //
    // //Change the questionName
    // questionName = StringUtil.toChangeQuestionName(questionName);
    //
    // String pageSize = "10";
    //
    // Page page = null;
    //
    // if (StringUtil.isEmpty(currentPage)) {
    // page = new Page();
    // } else {
    // page = new Page(pageSize, currentPage);
    // }
    //
    // List<Question> questionList =
    // questionService.findQuestionsByName(questionName, page);
    //
    // modelAndView.addObject("questionList", questionList);
    // modelAndView.addObject("page", page);
    //
    // modelAndView.setViewName("question_management");
    //
    // return modelAndView;
    // } else {
    // ModelAndView modelAndView = new ModelAndView();
    //
    // modelAndView.setViewName("redirect:showAll.action");
    //
    // return modelAndView;
    // }
    // }
    //
    // @RequestMapping(value = "showByQuestionId", method = RequestMethod.POST)
    // public ModelAndView showByQuestionId(String showByQuestionId) throws
    // ServletException, IOException {
    //
    // Question question = questionService.getQuestionById(showByQuestionId);
    //
    // ModelAndView modelAndView = new ModelAndView();
    //
    // modelAndView.addObject("question", question);
    // modelAndView.setViewName("question_detail");
    // return modelAndView;
    // }

}
