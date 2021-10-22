package com.example.controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.beans.Admin;
import com.example.beans.Expert;
import com.example.beans.Questions;
import com.example.beans.Results;
import com.example.beans.Student;
import com.example.beans.Subject;
import com.example.repository.AdminRepository;
import com.example.repository.ExpertRepository;
import com.example.repository.QuestionsRepository;
import com.example.repository.ResultsRepository;
import com.example.repository.StudentRepository;
import com.example.repository.SubjectRepository;


@Controller
public class MainController {
	
	static final String EXPERTLOGIN="expertlogin";
	static final String ADMINLOGIN="adminlogin";
	static final String REDIRECTTOED="redirect:/expertdashboard";
	static final String REDIRECTTOAD="redirect:/admindashboard";
	
	@Autowired
	private SubjectRepository subrepo;
	
	@Autowired
	private QuestionsRepository quesrepo;
	
	@Autowired
	private ExpertRepository expertrepo;
	
	@Autowired
	private AdminRepository adminrepo;
	
	@Autowired
	private StudentRepository stdrepo;
	
	@Autowired
	private ResultsRepository resrepo;
	
	
	@RequestMapping("/")
	public ModelAndView home() {
		return new ModelAndView("home");
	}
	@RequestMapping("/expertlogin")
	public ModelAndView loginasexpert() {
		return new ModelAndView(EXPERTLOGIN);
	}
	
	@RequestMapping("/adminlogin")
	public ModelAndView loginasadmin() {
		return new ModelAndView(ADMINLOGIN);
	}
	
	@GetMapping("/subjects/{std_id}")
	public ModelAndView getSubjects(@PathVariable("std_id") int stdid) {
		List<Subject> s=(List<Subject>) subrepo.findAll();
		ModelAndView mv=new ModelAndView();
		mv.addObject("std_id",stdid);
		mv.addObject("subs",s);
		mv.setViewName("subjects");
		return mv;
	}
	
	
//	/Student Module/
	
	@RequestMapping("/student/profile/{studentId}")
	public ModelAndView getProfile(@PathVariable("studentId")int stdid) {
		ModelAndView mv=new ModelAndView();
		Student student=stdrepo.getById(stdid);
		mv.addObject("student",student);
		mv.setViewName("studentprofile");
		return mv;
	}
	
	@RequestMapping("/student/register")
	public ModelAndView registerStudent(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("password") String password) {
		Student s=new Student();
		s.setStdName(name);
		s.setStdPassword(password);
		s.setEmail(email);
		stdrepo.save(s);
		return new ModelAndView("redirect:/subjects/"+s.getStdId());
	}
	
	@GetMapping("/student/login")
	public ModelAndView studentLogin() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	@GetMapping("/check/student/login")
	public ModelAndView checkloginStudent(@RequestParam("name") String name,@RequestParam("password") String password) {
		ModelAndView mv=new ModelAndView();
		Student s=stdrepo.findByNameAndPassword(name, password);
		if(s!=null) {
			return new ModelAndView("redirect:/subjects/"+s.getStdId());
		}else {
			mv.setViewName("login");
		}
		return mv;
	}
	
	
	@GetMapping("/getQuestions/{sub_id}")
	public ModelAndView getQuestions(@PathVariable("sub_id") int subid,@RequestParam("studentId") int stdid) {
		List<Questions> quests=quesrepo.getAllQuestionsBySubId(subid);
		ModelAndView mv=new ModelAndView();
		mv.addObject("std_id", stdid);
		mv.addObject("sub_id",subid);
		mv.addObject("questions", quests);
		mv.setViewName("questions");
		return mv;
	}
	
	@GetMapping("/getResult")
	public ModelAndView getResult(HttpServletRequest req) {
		int stdid=Integer.parseInt(req.getParameter("studentId"));
		int subid=Integer.parseInt(req.getParameter("subjectId"));
		ModelAndView mv=new ModelAndView();
		String []questionIds=req.getParameterValues("questionId");
		double result=0.0;
		for(String questionId:questionIds) {
			int quesid=Integer.parseInt(questionId);
			Questions q=quesrepo.getQuestionsById(quesid);
			String selectedOption=req.getParameter("question_"+questionId);
			String correctOption=q.getCorrectOption();
			if(selectedOption==null) {
				selectedOption="";
			}
			if(selectedOption.equals(correctOption)) {
				result+=1.0;
			}else {
				result-=0.25;
			}
		}
		Results res=new Results();
		res.setStdId(stdid);
		res.setSubId(subid);
		res.setScore(result);
		resrepo.save(res);
		mv.addObject("result",result);
		mv.setViewName("results");
		return mv;
	}
	
	
	
//	/Expert Module/
	@GetMapping("/expertdashboard")
	public ModelAndView expertDashboard() {
		return new ModelAndView("expertdashboard");
	}
	
	@GetMapping("/add/subject")
	public ModelAndView addSubject() {
		ModelAndView mv=new ModelAndView();
		List<Subject> subjects=subrepo.findAll();
		mv.addObject("subcs", subjects);
		mv.setViewName("addsubjects");
		return mv;
	}
	
	@GetMapping("/addsubject")
	public ModelAndView addSubject(@RequestParam("subjectName") String subjectName) {
		Subject s=new Subject();
		s.setSubName(subjectName);
		subrepo.save(s);
		return new ModelAndView("redirect:/add/subject");
	}
	
	@GetMapping("/add/question")
	public ModelAndView addQuestion() {
		ModelAndView mv=new ModelAndView();
		List<Subject> subjects=subrepo.findAll();
		mv.addObject("sbs", subjects);
		mv.setViewName("addquestions");
		return mv;
	}
	
	@RequestMapping("/addquestions")
	public ModelAndView addQuestions(HttpServletRequest req) {
		Questions ques=new Questions();
		String subName=req.getParameter("subjectName");
		String quesdescription=req.getParameter("questiondescription");
		String option1=req.getParameter("option1");
		String option2=req.getParameter("option2");
		String option3=req.getParameter("option3");
		String option4=req.getParameter("option4");
		String correctoption=req.getParameter("correctoption");
		Subject subject=subrepo.getByName(subName);
		ques.setSubId(subject.getSubId());
		ques.setQuesDescription(quesdescription);
		ques.setOption1(option1);
		ques.setOption2(option2);
		ques.setOption3(option3);
		ques.setOption4(option4);
		ques.setCorrectOption(correctoption);
		quesrepo.save(ques);
		return new ModelAndView(REDIRECTTOED);
	}
	
	
	
	@GetMapping("/check/expert/login")
	public ModelAndView checkExpert(@RequestParam("name") String name,@RequestParam("password") String password) {
		ModelAndView mv=new ModelAndView();
		Expert e=expertrepo.findByNameAndPassword(name, password);
		if(e!=null) {
			return new ModelAndView(REDIRECTTOED);
		}else {
			mv.setViewName("expertlogin");
		}
		return mv;
	}
	
//	
//	
//	/Admin Module/
	
	@GetMapping("/admindashboard")
	public ModelAndView adminDashboard() {
		return new ModelAndView("/admindashboard");
	}
	
	
	@GetMapping("/check/admin/login")
	public ModelAndView checkAdmin(@RequestParam("name") String name,@RequestParam("password") String password) {
		ModelAndView mv=new ModelAndView();
		Admin a=adminrepo.findByNameAndPassword(name, password);
		if(a!=null) {
			return new ModelAndView(REDIRECTTOAD);
		}else {
			mv.setViewName(ADMINLOGIN);
		}
		return mv;
	}
	
//	
//	/Add Expert/
	
	@GetMapping("/addexpert")
	public ModelAndView addExpert() {
		return new ModelAndView("addexpert");
	}
	
	@GetMapping("/addstudent")
	public ModelAndView addStudent() {
		return new ModelAndView("addstudent");
	}
	
	@PostMapping("/add/expert")
	public ModelAndView addExpert(@RequestParam("name") String name,@RequestParam("password") String password) {
		Expert e=new Expert();
		e.setExpertName(name);
		e.setExpertPassword(password);
		expertrepo.save(e);
		return new ModelAndView(REDIRECTTOAD);
	}
	
//	/Delete Expert/
	@GetMapping("/delete/expert")
	public ModelAndView deleteExpert(@RequestParam("id")String id) {
		int expertid=Integer.parseInt(id);
		expertrepo.deleteById(expertid);
		return new ModelAndView(REDIRECTTOAD);
	}
	
//	/Add Student/
	@PostMapping("/add/student")
	public ModelAndView addStudent(@RequestParam("name") String name,@RequestParam("password") String password,@RequestParam("email")String email) {
		Student s=new Student();
		s.setStdName(name);
		s.setStdPassword(password);
		s.setEmail(email);
		stdrepo.save(s);
		return new ModelAndView(REDIRECTTOAD);
	}
	
//	/Delete Student/
	@GetMapping("/delete/student")
	public ModelAndView deleteStudent(@RequestParam("id")String id) {
		int stdid=Integer.parseInt(id);
		stdrepo.deleteById(stdid);
		return new ModelAndView(REDIRECTTOAD);
	}
	
	@GetMapping("/view/students")
	public ModelAndView viewStudents() {
		ModelAndView mv=new ModelAndView();
		List<Student> students=stdrepo.findAll();
		mv.addObject("students", students);
		mv.setViewName("studentlist");
		return mv;
	}
	
	@GetMapping("/view/experts")
	public ModelAndView viewExperts() {
		ModelAndView mv=new ModelAndView();
		List<Expert> experts=expertrepo.findAll();
		mv.addObject("experts", experts);
		mv.setViewName("expertlist");
		return mv;
	}
//	
//	/View Results/
	@GetMapping("/view/results")
	public ModelAndView viewResults() {
		ModelAndView mv=new ModelAndView();
		List<Results> results=resrepo.findAll();
		mv.addObject("results", results);
		mv.setViewName("studentresults");
		return mv;
	}
//	
//	/Controller Module/
//	@PostMapping("/controller/login")
//	public ModelAndView checkController(@RequestParam("name") String name,@RequestParam("password") String password) {
//		if(name.equals("appcontroller") && password.equals("controller@123")) {
//			return new ModelAndView("redirect:/subjects");
//		}
//		return new ModelAndView("redirect:/subjects");
//	}
//	
//	/Add Admin/
	@PostMapping("/add/admin")
	public ModelAndView addAdmin(@RequestParam("name") String name,@RequestParam("password") String password) {
		ModelAndView mv=new ModelAndView();
		Admin a=new Admin();
		a.setAdminName(name);
		a.setAdminPassword(password);
		adminrepo.save(a);
		mv.setViewName("subjects");
		return mv;
	}
	
	
	
}