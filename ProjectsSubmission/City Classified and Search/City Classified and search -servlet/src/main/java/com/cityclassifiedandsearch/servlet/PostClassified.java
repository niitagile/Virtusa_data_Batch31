package com.cityclassifiedandsearch.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cityclassifiedandsearch.bean.Classified;
import com.cityclassifiedandsearch.dao.ClassifiedDAO;
import com.cityclassifiedandsearch.util.MyUtil;

@WebServlet("/PostClassified")
public class PostClassified extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostClassified() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String classifiedCategory= request.getParameter("classifiedCategory");
		String description= request.getParameter("description"); 
		String classifiedTitle= request.getParameter("classifiedTitle");
			
		Classified classified=new Classified();
		classified.setUserId(MyUtil.getLoginedUser(request.getSession()).getUserId());
		classified.setClassifiedCategory(classifiedCategory);
		classified.setClassifiedTitle(classifiedTitle);
		classified.setDescription(description);
		ClassifiedDAO.addClassified(classified);
		response.sendRedirect("welcome.jsp");
	}
}