package com.cityclassifiedandsearch.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cityclassifiedandsearch.dao.CityDAO;

/**
 * Servlet implementation class DeleteCity
 */
@WebServlet("/DeleteCity")
public class DeleteCity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCity() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cityId=Integer.parseInt(request.getParameter("cityId"));
		CityDAO.deleteCity(cityId);
		response.sendRedirect("citydetails.jsp");
		
	}

}
