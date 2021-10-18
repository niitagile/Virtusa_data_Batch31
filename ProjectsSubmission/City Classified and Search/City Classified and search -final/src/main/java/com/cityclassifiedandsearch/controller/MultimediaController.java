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

import com.cityclassifiedandsearch.repo.MultimediaRepo;
import com.cityclassifiedandsearch.repo.UserRepository;
import com.cityclassifiedandsearch.service.MultimediaService;

@Controller
public class MultimediaController {
	
	@Autowired
	MultimediaService multiservice;
	
	@Autowired
	MultimediaRepo multimediarepo;
	
	@Autowired
	private UserRepository userRepository;
	
	public int getCurrentUserId(Authentication authentication) {
		return (userRepository.findByUserEmail(authentication.getName())).getUserId();
	}
	
	//user
	@GetMapping("/user/viewupdates")
	public String viewCarouselDetailsUser(Model model) {
		model.addAttribute("carousel", multiservice.getAllCarousel());
		return "carousel";
	}
	
	//admin
	/*View carousel details*/
	@GetMapping("/admin/viewupdates")
	public String viewCarouselDetails(Model model) {
		model.addAttribute("carousel", multiservice.getAllCarousel());
		return "multimediaupdates";
	}
	
	/*Post carousel details*/
	@GetMapping("/admin/postupdates")
	public String postCarouselDetailsform(Model model) {
		model.addAttribute("carousel", multiservice.getAllCarousel());
		return "postupdates";
	}
	@PostMapping("/admin/postupdates")
	public String postCarouselDetails(Authentication authentication,
			@RequestParam("careouselTitle")String careouselTitle,
			@RequestParam("update")String update,
			@RequestParam("file") MultipartFile image) throws IOException {
		try {
			if(image.isEmpty()) {
				multiservice.postCarousel(getCurrentUserId(authentication),careouselTitle,update);
			}
			else {
				multiservice.postCarousel(getCurrentUserId(authentication),careouselTitle,update,image);
			}
		}
		catch (IOException e) {
			return "redirect:/admin/postupdates?status=failed";
		}
		
		return "redirect:/admin/postupdates?status=success";
	}
	
	/*Update carousel details*/
	@GetMapping("/admin/editupdates/{carouselid}")
	public String editCarouselDetailsform(@PathVariable("carouselid")int carouselid,Model model) {
		model.addAttribute("carousel", multiservice.getCarouselById(carouselid));
		return "editupdates";
	}
	@PostMapping("/admin/editupdates/{carouselid}")
	public String updateCarouselDetails(@PathVariable("carouselid")int carouselId,
			@RequestParam("careouselTitle")String careouselTitle,
			@RequestParam("update")String update,
			@RequestParam("file") MultipartFile image) throws IOException {
		try {
			if(image.isEmpty()) {
				multiservice.updateCarousel(carouselId,careouselTitle,update);
			}
			else {
				multiservice.updateCarousel(carouselId,careouselTitle,update,image);
			}
		}
		catch (IOException e) {
			return "redirect:/admin/editupdates" + carouselId + "?status=failed";
		}
		return "redirect:/admin/editupdates/" + carouselId + "?status=success";
	}
	
	/*Delete carousel Details*/
	@GetMapping("/admin/deleteupdates/{carouselId}")
	public String deleteCityDetails(@PathVariable("carouselId")int carouselId) {
		multiservice.deleteCarousel(carouselId);
		return "redirect:/admin/viewupdates?delete-status=success";
	}
}
