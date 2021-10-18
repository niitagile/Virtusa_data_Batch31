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

/**
 * Servlet implementation class UpdateCitydetails
 */
@WebServlet("/UpdateCity")
public class UpdateCitydetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCitydetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cityId=Integer.parseInt(request.getParameter("cityId"));
		String category=request.getParameter("category");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String link=request.getParameter("link");
		Citydetails citydetails=new Citydetails();
		citydetails.setUserId(MyUtil.getLoginedUser(request.getSession()).getUserId());
		citydetails.setCityId(cityId);
		citydetails.setCategory(category);
		citydetails.setAddress(address);
		citydetails.setName(name);
		citydetails.setCity(city);
		citydetails.setLink(link);
		CityDAO.updateCity(citydetails);
		response.sendRedirect("citydetails.jsp");
	}

}
