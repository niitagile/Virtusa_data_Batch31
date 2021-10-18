package com.cityclassifiedandsearch.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cityclassifiedandsearch.dao.ClassifiedDAO;

/**
 * Servlet implementation class DeleteClassified
 */
@WebServlet("/DeleteClassified")
public class DeleteClassified extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteClassified() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int classifiedId=Integer.parseInt(request.getParameter("classifiedId"));
		if(ClassifiedDAO.deleteClassified(classifiedId))
		response.sendRedirect("myclassifieds.jsp");
		else
			System.out.print("Cant Delete "+classifiedId);
	}

}
