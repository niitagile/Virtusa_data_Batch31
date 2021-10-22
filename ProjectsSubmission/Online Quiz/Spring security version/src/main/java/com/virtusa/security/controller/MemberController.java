package com.virtusa.security.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.security.model.Admin;
import com.virtusa.security.model.Expert;
import com.virtusa.security.model.Member;
import com.virtusa.security.model.Questions;
import com.virtusa.security.model.Results;
import com.virtusa.security.model.Student;
import com.virtusa.security.model.Subject;
import com.virtusa.security.repository.AdminRepository;
import com.virtusa.security.repository.ExpertRepository;
import com.virtusa.security.repository.QuestionsRepository;
import com.virtusa.security.repository.ResultsRepository;
import com.virtusa.security.repository.StudentRepository;
import com.virtusa.security.repository.SubjectRepository;
import com.virtusa.security.service.MemberService;
import com.virtusa.security.validator.MemberValidator;

@Controller
public class MemberController {
	
	static final String EXPERTLOGIN="expertlogin";
	static final String ADMINLOGIN="adminlogin";
	static final String REDIRECTTOED="redirect:/expertdashboard";
	static final String REDIRECTTOAD="redirect:/admindashboard";
	
    @Autowired
    private MemberService memservice;

    @Autowired
    private MemberValidator memvalidator;
    
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

	/* security */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new Member());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") Member userForm, BindingResult bindingResult, Model model) {
        memvalidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        memservice.save(userForm);
        Student s=new Student();
        s.setEmail(userForm.getMail());
        s.setStdName(userForm.getUsername());
        int id=userForm.getId().intValue();
        s.setStdId(id);
        stdrepo.save(s);
        
        return "redirect:/subjects/"+userForm.getId();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    

//    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
//    public String welcome(Model model) {
//    	return "redirect:/subjects/";
//    }
    /* security */
    
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
		List<Results> results=resrepo.findResultsById(stdid);
		mv.addObject("results",results);
		mv.addObject("student",student);
		mv.setViewName("studentprofile");
		return mv;
	}
	
	@RequestMapping("/student/register")
	public ModelAndView registerStudent(@RequestParam("yname") String name,@RequestParam("email") String email,@RequestParam("password") String password) {
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
	public ModelAndView checkloginStudent(@RequestParam("yname") String name,@RequestParam("password") String password) {
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