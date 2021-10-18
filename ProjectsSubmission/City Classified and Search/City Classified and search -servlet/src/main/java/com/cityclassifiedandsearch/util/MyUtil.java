package com.cityclassifiedandsearch.util;

import javax.servlet.http.HttpSession;

import com.cityclassifiedandsearch.bean.User;

public class MyUtil {
	 public static User getLoginedUser(HttpSession session)//return stored user details
	 {
	        User loginedUser = (User) session.getAttribute("user");
	        return loginedUser;
	  }

	public static void storeLoginedUser(HttpSession session, User user) //store user in a session
	{
		 session.setAttribute("user", user);	
	}
}
