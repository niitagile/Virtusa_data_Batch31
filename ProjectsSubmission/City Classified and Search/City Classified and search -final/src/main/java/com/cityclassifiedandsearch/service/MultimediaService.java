package com.cityclassifiedandsearch.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cityclassifiedandsearch.bean.Multimedia;
import com.cityclassifiedandsearch.repo.MultimediaRepo;

@Service
public class MultimediaService {
	@Autowired
	MultimediaRepo multimediarepo;

	public MultimediaService(MultimediaRepo multimediarepo) {
		super();
		this.multimediarepo = multimediarepo;
	}
	
	//ADMIN
	/*View Carousel details*/
	public List<Multimedia> getAllCarousel(){
		List<Multimedia> carousel=multimediarepo.findAll();
		if(carousel.size() > 0) {
			Collections.reverse(carousel);
			return carousel;
		}
		return new ArrayList<Multimedia>();
	}
	public Multimedia getCarouselById(int carouselid) {
		Optional<Multimedia> carousel = multimediarepo.findById(carouselid);
		   if(carousel.isPresent()) {
			   return carousel.get();
		   }
		   else {
			   return null; //replace with custom exception(RecordNotFoundException)
		   }
	}
	
	/*Post Carousel details*/
	public Multimedia postCarousel(int userId, String careouselTitle, String update, MultipartFile image) throws IOException {
		Multimedia newCarousel= new Multimedia();
		newCarousel.setUserId(userId);
		newCarousel.setCarouselTitle(careouselTitle);
		newCarousel.setCarouselupdate(update);
		newCarousel.setCarouselimage(Base64.getEncoder().encodeToString(image.getBytes()));
		return multimediarepo.save(newCarousel);
	}
	public Multimedia postCarousel(int userId, String careouselTitle, String update) {
		Multimedia newCarousel= new Multimedia();
		newCarousel.setUserId(userId);
		newCarousel.setCarouselTitle(careouselTitle);
		newCarousel.setCarouselupdate(update);
		return multimediarepo.save(newCarousel);
		
	}

	/*Update Carousel details*/
	public Multimedia updateCarousel(int carouselId, String careouselTitle, String update, MultipartFile image) throws IOException {
		Optional<Multimedia> exist = multimediarepo.findById(carouselId);
		   if(exist.isPresent()) {
			   Multimedia newCarousel= exist.get();
				newCarousel.setCarouselTitle(careouselTitle);
				newCarousel.setCarouselupdate(update);
				newCarousel.setCarouselimage(Base64.getEncoder().encodeToString(image.getBytes()));
				 return multimediarepo.save(newCarousel);
		   }
		return null;
		
	}
	public Multimedia updateCarousel(int carouselId, String careouselTitle, String update) {
		Optional<Multimedia> exist = multimediarepo.findById(carouselId);
		   if(exist.isPresent()) {
			   Multimedia newCarousel= exist.get();
				newCarousel.setCarouselTitle(careouselTitle);
				newCarousel.setCarouselupdate(update);
				 return multimediarepo.save(newCarousel);
		   }
		return null;
		
	}

	/*Delete Carousel details*/
	public void deleteCarousel(int carouselId) {
		Optional<Multimedia> exist = multimediarepo.findById(carouselId);
		   if(exist.isPresent()) {
	           multimediarepo.deleteById(carouselId);
	       }
	}

}
