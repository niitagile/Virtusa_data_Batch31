package com.cityclassifiedandsearch.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cityclassifiedandsearch.bean.Citydetails;
import com.cityclassifiedandsearch.dao.CityDAO;
import com.cityclassifiedandsearch.util.MyUtil;
/**
 * Servlet implementation class SearchCity
 */
@WebServlet("/SearchCity")
public class SearchCity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCity() {
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
		 List<Citydetails> list=CityDAO.getAllCity();
		 
		 if(search.length()>0) {
			 for(Citydetails cs:list){
				if(cs.getCity().startsWith(search)) {
					response.getWriter().println("City: "+cs.getCity()+"<br>"+"Category: "+cs.getCategory()+"<br>"
					+"Name: "+cs.getName()+"<br>"
					+"Address: "+cs.getAddress()+"<br>"
					+"Link"+"<a href='"+cs.getLink()+">"+cs.getLink()+"</a><br><br>");
				}
			 }
		 }
					
	}

}
