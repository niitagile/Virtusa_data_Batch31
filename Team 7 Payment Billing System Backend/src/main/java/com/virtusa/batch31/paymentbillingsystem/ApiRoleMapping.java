package com.virtusa.batch31.paymentbillingsystem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;


@Component
public class ApiRoleMapping {
	private final String ADMIN = "admin";
	private final String ACCOUNTANT = "accountant";
	private final String GET = "GET";
	private final String POST = "POST";
	private final String PUT = "PUT";
	// private final String DELETE = "DELETE";
	private List<String> ADMIN_ALLOWED = Arrays.asList(ADMIN);
	private List<String> ACCOUNTANT_ALLOWED = Arrays.asList(ACCOUNTANT);
	private List<String> ADMIN_AND_ACCOUNTANT_ALLOWED = Arrays.asList(ADMIN, ACCOUNTANT);
	private Map<String, List<String>> GET_MAPPINGS = new HashMap<>();
	private Map<String, List<String>> POST_MAPPINGS = new HashMap<>();
	private Map<String, List<String>> PUT_MAPPINGS = new HashMap<>();
	private Map<String, List<String>> DELETE_MAPPINGS = new HashMap<>();
	public ApiRoleMapping() {
		super();
		setGetMappings();
		setPostMappings();
		setPutMappings();
		setDeleteMappings();
	}
	
	public List<String> getAllowedRoles(String uri, String method) {
		if(method.equals(GET)){
			return findRoles(GET_MAPPINGS, uri);
		}
		else if(method.equals(POST)){
			return findRoles(POST_MAPPINGS, uri);
		}
		else if(method.equals(PUT)){
			return findRoles(PUT_MAPPINGS, uri);
		}
		else {
			return findRoles(DELETE_MAPPINGS, uri);
		}
	}

	private List<String> findRoles(Map<String, List<String>> map, String uri){
		for(Map.Entry<String, List<String>> entry : map.entrySet()){
			String pattern = entry.getKey();
			if(Pattern.matches(pattern, uri)){
				System.out.println(pattern);
				return entry.getValue();
			}
		}
		return null;
	}

	//Get Mappings
	private void setGetMappings(){
		setBranchGetMappings();
		setAccountantGetMappings();
		setCourseGetMappings();
		setPaymentDetailGetMappings();
		setStudentGetMappings();
	}

	private void setBranchGetMappings(){
		GET_MAPPINGS.put("/branch/", ADMIN_ALLOWED);
		GET_MAPPINGS.put("/branch/[0-9]+", ADMIN_ALLOWED);
		GET_MAPPINGS.put("/branch/students/[0-9]+", ADMIN_AND_ACCOUNTANT_ALLOWED);
	}

	private void setAccountantGetMappings(){
		GET_MAPPINGS.put("/accountant/", ADMIN_ALLOWED);
		GET_MAPPINGS.put("/accountant/[0-9]+", ADMIN_AND_ACCOUNTANT_ALLOWED);
		GET_MAPPINGS.put("/accountant/branch/[0-9]+", ADMIN_AND_ACCOUNTANT_ALLOWED);				
	}

	private void setCourseGetMappings(){
		GET_MAPPINGS.put("/course/", ADMIN_AND_ACCOUNTANT_ALLOWED);
		GET_MAPPINGS.put("/course/[0-9]+", ADMIN_ALLOWED);
	}

	private void setPaymentDetailGetMappings(){
		GET_MAPPINGS.put("/paymentdetail/", ACCOUNTANT_ALLOWED);
		GET_MAPPINGS.put("/paymentdetail/[0-9]+", ACCOUNTANT_ALLOWED);
	}

	private void setStudentGetMappings(){
		GET_MAPPINGS.put("/student/", ADMIN_AND_ACCOUNTANT_ALLOWED);
		GET_MAPPINGS.put("/student/[0-9]+", ADMIN_AND_ACCOUNTANT_ALLOWED);
		GET_MAPPINGS.put("/student/course/[0-9]+", ADMIN_AND_ACCOUNTANT_ALLOWED);
		GET_MAPPINGS.put("/student/fee/[0-9]+", ACCOUNTANT_ALLOWED);
	}
	
	//Post Mappings
	private void setPostMappings(){
		setBranchPostMappings();
		setAccountantPostMappings();
		setCoursePostMappings();
		setPaymentDetailPostMappings();
		setStudentPostMappings();
	}

	private void setBranchPostMappings(){
		POST_MAPPINGS.put("/branch/", ADMIN_ALLOWED);
	}

	private void setAccountantPostMappings(){
		POST_MAPPINGS.put("/accountant/[0-9]+", ADMIN_ALLOWED);
	}

	private void setCoursePostMappings(){
		POST_MAPPINGS.put("/course/", ADMIN_ALLOWED);
	}

	private void setPaymentDetailPostMappings(){
		POST_MAPPINGS.put("/paymentdetail/[0-9]+", ACCOUNTANT_ALLOWED);
	}

	private void setStudentPostMappings(){
		POST_MAPPINGS.put("/student/[0-9]+/[0-9]+", ACCOUNTANT_ALLOWED);
	}

	//Put Mappings
	private void setPutMappings(){
		setBranchPutMappings();
		setAccountantPutMappings();
		setCoursePutMappings();
		setPaymentDetailPutMappings();
		setStudentPutMappings();
	}

	private void setBranchPutMappings(){
		PUT_MAPPINGS.put("/branch/", ADMIN_ALLOWED);
	}

	private void setAccountantPutMappings(){
		PUT_MAPPINGS.put("/accountant/", ADMIN_ALLOWED);
	}

	private void setCoursePutMappings(){
		PUT_MAPPINGS.put("/course/", ADMIN_ALLOWED);
	}

	private void setPaymentDetailPutMappings(){
		PUT_MAPPINGS.put("/paymentdetail/", ACCOUNTANT_ALLOWED);
	}

	private void setStudentPutMappings(){
		PUT_MAPPINGS.put("/student/", ADMIN_AND_ACCOUNTANT_ALLOWED);
	}

	//Delete Mappings
	private void setDeleteMappings(){
		setBranchDeleteMappings();
		setAccountantDeleteMappings();
		setCourseDeleteMappings();
		setPaymentDetailDeleteMappings();
		setStudentDeleteMappings();
	}

	private void setBranchDeleteMappings(){
		DELETE_MAPPINGS.put("/branch/[0-9]+", ADMIN_ALLOWED);
	}

	private void setAccountantDeleteMappings(){
		DELETE_MAPPINGS.put("/accountant/[0-9]+", ADMIN_ALLOWED);
	}

	private void setCourseDeleteMappings(){
		DELETE_MAPPINGS.put("/course/[0-9]+", ADMIN_ALLOWED);
	}

	private void setPaymentDetailDeleteMappings(){
		DELETE_MAPPINGS.put("/paymentdetail/[0-9]+", ACCOUNTANT_ALLOWED);
	}

	private void setStudentDeleteMappings(){
		DELETE_MAPPINGS.put("/student/[0-9]+", ADMIN_ALLOWED);
	}


}
