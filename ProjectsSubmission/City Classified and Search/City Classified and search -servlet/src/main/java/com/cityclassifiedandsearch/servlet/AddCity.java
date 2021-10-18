package com.cityclassifiedandsearch.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cityclassifiedandsearch.bean.Citydetails;
import com.cityclassifiedandsearch.dao.CityDAO;
import com.cityclassifiedandsearch.util.MyUtil;

@WebServlet("/AddCity")
public class AddCity extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public AddCity() {
		super();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category=request.getParameter("category");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String link=request.getParameter("link");
		Citydetails citydetails=new Citydetails();
		citydetails.setUserId(MyUtil.getLoginedUser(request.getSession()).getUserId());
		citydetails.setCategory(category);
		citydetails.setAddress(address);
		citydetails.setName(name);
		citydetails.setCity(city);
		citydetails.setLink(link);
		CityDAO.addCity(citydetails);
		response.sendRedirect("citydetails.jsp");
	}

}
