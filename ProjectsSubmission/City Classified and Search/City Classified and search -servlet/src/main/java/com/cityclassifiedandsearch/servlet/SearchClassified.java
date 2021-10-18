package com.cityclassifiedandsearch.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cityclassifiedandsearch.bean.Citydetails;
import com.cityclassifiedandsearch.bean.Classified;
import com.cityclassifiedandsearch.bean.User;
import com.cityclassifiedandsearch.dao.CityDAO;
import com.cityclassifiedandsearch.dao.ClassifiedDAO;
import com.cityclassifiedandsearch.util.MyUtil;

/**
 * Servlet implementation class SearchClassified
 */
@WebServlet("/SearchClassified")
public class SearchClassified extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchClassified() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String search=request.getParameter("search");
		 List<Classified> list=ClassifiedDAO.getAllClassified();
		 if(search.length()>0) {
			 for(Classified cs:list){
				if(cs.getClassifiedCategory().startsWith(search)) {
					User user=cs.getUser();
					response.getWriter().println("Category:"+cs.getClassifiedCategory()+"<br>"
					+"Title:"+cs.getClassifiedTitle()+"<br>"
					+"Description:"+cs.getDescription()+"<br>"
					+"Name:"+user.getUserName()+"<br>"
					+"Contact:"+user.getMobile()+"<br>"
					+"Address:"+user.getUserAddress()+"<br>"
					+"City:"+user.getUserCity()+"<br>"
					+"Email"+user.getUserEmail()+"<br><br><br>");
					
				}
			 }
		 }
		
	}

}
