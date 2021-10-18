package com.cityclassifiedandsearch.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cityclassifiedandsearch.bean.CityDetails;
import com.cityclassifiedandsearch.repo.CityDetailsRepository;
import com.cityclassifiedandsearch.repo.UserRepository;
import com.cityclassifiedandsearch.service.CityDetailsService;
import com.cityclassifiedandsearch.service.UserServiceImpl;

@Controller
public class CityDetailsController {
	@Autowired
	private CityDetailsService cityDetailsService;
	
	@Autowired
	private CityDetailsRepository cityDetailsRepository;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserRepository userRepository;
	
	public int getCurrentUserId(Authentication authentication) {
		return (userRepository.findByUserEmail(authentication.getName())).getUserId();
	}
	
	//Guest
	@GetMapping("/index2")
	public String index2(Model model) {
		model.addAttribute("cityDetails", cityDetailsService.getAllCityDetails());
		return "index2";
	}
	@GetMapping("/viewcitydetails/{cityId}")
	public String viewCityDetails(Model model, @PathVariable("cityId") int cityId) {
		CityDetails cityDetails = cityDetailsService.getCityDetailsById(cityId);
		model.addAttribute("cityDetails", cityDetails);
		model.addAttribute("userDetails", userServiceImpl.getUserById(cityDetails.getUserId()));
		return "viewcitydetails";
	}
	
	//User
	@GetMapping("/user/index2")
	public String userIndex2(Model model) {
		model.addAttribute("cityDetails", cityDetailsService.getAllCityDetails());
		return "userindex2";
	}
	@GetMapping("/user/viewcitydetails/{cityId}")
	public String userViewCityDetails(Model model, @PathVariable("cityId") int cityId) {
		CityDetails cityDetails = cityDetailsService.getCityDetailsById(cityId);
		model.addAttribute("cityDetails", cityDetails);
		model.addAttribute("userDetails", userServiceImpl.getUserById(cityDetails.getUserId()));
		return "userviewcitydetails";
	}
	@PostMapping("/user/searchcitydetails")
	public String userSearchCityDetails(@RequestParam("key") String key, Model model) {
		model.addAttribute("cityDetails", cityDetailsService.searchCityDetails(key));
		return "usersearchcitydetails";
	}
	
	//Admin
	@GetMapping("/admin/index2")
	public String adminIndex2(Model model, Authentication authentication) {
		model.addAttribute("cityDetails", cityDetailsService.getAllCityDetails());
		model.addAttribute("currentUserId", getCurrentUserId(authentication));
		return "adminindex2";
	}
	@GetMapping("/admin/viewcitydetails/{cityId}")
	public String adminViewCityDetails(Model model, @PathVariable("cityId") int cityId) {
		CityDetails cityDetails = cityDetailsService.getCityDetailsById(cityId);
		model.addAttribute("cityDetails", cityDetails);
		model.addAttribute("userDetails", userServiceImpl.getUserById(cityDetails.getUserId()));
		return "adminviewcitydetails";
	}
	@GetMapping("/admin/postcitydetails")
	public String postCityDetailsForm() {
		return "postcitydetails";
	}
	@PostMapping("/admin/postcitydetails")
	public String postCityDetails(Authentication authentication,
			@RequestParam("category")String category,
			@RequestParam("name")String name,
			@RequestParam("address")String address,
			@RequestParam("cityName")String cityName,
			@RequestParam("link")String link,
			@RequestParam("file") MultipartFile image) throws IOException {
		try {
			if(image.isEmpty()) {
				cityDetailsService.createCityDetails(getCurrentUserId(authentication), category, name, address, cityName, link);
			}
			else {
				cityDetailsService.createCityDetails(getCurrentUserId(authentication), category, name, address, cityName, link, image);
			}
		}
		catch (IOException e) {
			return "redirect:/admin/postcitydetails?status=failed";
		}
		return "redirect:/admin/postcitydetails?status=success";
	}
	@GetMapping("/admin/mycitydetails")
	public String myCityDetails(Authentication authentication, Model model) {
		model.addAttribute("cityDetails", cityDetailsService.getCityDetailsByUserId(getCurrentUserId(authentication)));
		return "mycitydetails";
	}
	@GetMapping("/admin/editcitydetails/{cityId}")
	public String editCityDetailsForm(@PathVariable("cityId") int cityId, Model model) {
		model.addAttribute("cityDetails", cityDetailsService.getCityDetailsById(cityId));
		return "editcitydetails";
	}
	@PostMapping("/admin/editcitydetails/{cityId}")
	public String editCityDetails(@PathVariable("cityId") int cityId, @RequestParam("category")String category,
			@RequestParam("name")String name,
			@RequestParam("address")String address,
			@RequestParam("cityName")String cityName,
			@RequestParam("link")String link,
			@RequestParam("file") MultipartFile image,
			Authentication authentication) throws IOException {
		int currentUserId = getCurrentUserId(authentication);
		if(currentUserId != cityDetailsRepository.getById(cityId).getUserId()) {
			return "redirect:/admin/editcitydetails/" + cityId + "?status=failed";
		}
		try {
			if(image.isEmpty()) {
				cityDetailsService.updateCityDetails(cityId, category, name, address, cityName, link);
			}
			else {
				cityDetailsService.updateCityDetails(cityId, category, name, address, cityName, link, image);
			}
		}
		catch (IOException e) {
			return "redirect:/admin/editcitydetails/" + cityId + "?status=failed";
		}
		return "redirect:/admin/editcitydetails/" + cityId + "?status=success";
	}
	@GetMapping("/admin/deletecitydetails/{cityId}")
	public String deleteCityDetails(@PathVariable("cityId")int cityId, Authentication authentication) {
		int currentUserId = getCurrentUserId(authentication);
		if(currentUserId == cityDetailsRepository.getById(cityId).getUserId()) {
			cityDetailsService.deleteCityDetailsById(cityId);
			return "redirect:/admin/mycitydetails?delete-status=success";
		}
		else {
			return "redirect:/admin/mycitydetails?delete-status=error";
		}
	}
	@PostMapping("/admin/searchcitydetails")
	public String adminSearchCity(@RequestParam("key") String key, Authentication authentication, Model model) {
		model.addAttribute("currentUserId", getCurrentUserId(authentication));
		model.addAttribute("cityDetails", cityDetailsService.searchCityDetails(key));
		return "adminsearchcitydetails";
	}
}
