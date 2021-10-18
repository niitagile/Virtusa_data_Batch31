package com.cityclassifiedandsearch.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cityclassifiedandsearch.bean.Classified;
import com.cityclassifiedandsearch.dao.ClassifiedDAO;
import com.cityclassifiedandsearch.util.MyUtil;

/**
 * Servlet implementation class UpdateClassified
 */
@WebServlet("/UpdateClassified")
public class UpdateClassified extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int classifiedId=Integer.parseInt(request.getParameter("classifiedId"));
		String classifiedCategory= request.getParameter("classifiedCategory");
		String description= request.getParameter("description"); 
		String classifiedTitle= request.getParameter("classifiedTitle");
		Classified classified=new Classified();
		classified.setClassifiedId(classifiedId);
		classified.setUserId(MyUtil.getLoginedUser(request.getSession()).getUserId());
		classified.setClassifiedCategory(classifiedCategory);
		classified.setClassifiedTitle(classifiedTitle);
		classified.setDescription(description);
		ClassifiedDAO.updateClassified(classified);
		response.sendRedirect("welcome.jsp");
	}

}
