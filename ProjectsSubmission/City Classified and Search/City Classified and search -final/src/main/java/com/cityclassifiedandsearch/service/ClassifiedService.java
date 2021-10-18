package com.cityclassifiedandsearch.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cityclassifiedandsearch.bean.Classified;
import com.cityclassifiedandsearch.repo.ClassifiedRepository;

@Service
public class ClassifiedService {
	@Autowired
	private ClassifiedRepository classifiedRepository;
	
	public List<Classified> getAllClassifieds() {
		List<Classified> classifieds = classifiedRepository.findAll();
		if(classifieds.size() > 0) {
			Collections.reverse(classifieds);
			return classifieds;
		} else {
		    return new ArrayList<Classified>(); //replace with custom exception(RecordNotFoundException)
		}
    }
	
	public Classified getClassifiedById(int classifiedId) {
	   Optional<Classified> classified = classifiedRepository.findById(classifiedId);
	   if(classified.isPresent()) {
		   return classified.get();
	   }
	   else {
		   return null; //replace with custom exception(RecordNotFoundException)
	   }
	}
   
	public List<Classified> approvalClassifieds() {
		List<Classified> classifieds = classifiedRepository.findByApproval(false);
		if(classifieds.size() > 0) {
			Collections.reverse(classifieds);
			return classifieds;
		} else {
		    return new ArrayList<Classified>(); //replace with custom exception(RecordNotFoundException)
		}
	}
   
    public List<Classified> getClassifiedByUserId(int userId) {
	   List<Classified> classifieds = classifiedRepository.findByUserId(userId);
		if(classifieds.size() > 0) {
			Collections.reverse(classifieds);
			return classifieds;
		} else {
		    return new ArrayList<Classified>(); //replace with custom exception(RecordNotFoundException)
		}
    }
   
    public Classified createClassified(int userId, String classifiedCategory, String classifiedTitle, String description, MultipartFile image) throws IOException {
	   Classified newClassified = new Classified();
	   newClassified.setUserId(userId);
	   newClassified.setClassifiedTitle(classifiedTitle);
	   newClassified.setClassifiedCategory(classifiedCategory);
	   newClassified.setDescription(description);
	   newClassified.setApproval(false);
	   String filename = StringUtils.cleanPath(image.getOriginalFilename());
	   if(filename.contains("..")) {
		   System.out.println("not a valid file");
	   }
	   newClassified.setClassifiedimage(Base64.getEncoder().encodeToString(image.getBytes()));
	   return classifiedRepository.save(newClassified);
    }
   
    public Classified createClassified(int userId, String classifiedCategory, String classifiedTitle, String description) throws IOException {
	   Classified newClassified = new Classified();
	   newClassified.setUserId(userId);
	   newClassified.setClassifiedTitle(classifiedTitle);
	   newClassified.setClassifiedCategory(classifiedCategory);
	   newClassified.setDescription(description);
	   newClassified.setApproval(false);
	   return classifiedRepository.save(newClassified);
    }
   
    public void deleteClassifiedById(int classifiedId) {
       Optional<Classified> classified = classifiedRepository.findById(classifiedId);
       if(classified.isPresent()) {
           classifiedRepository.deleteById(classifiedId);
       }
       else {
           return; // replace with custom exception(RecordNotFoundException)
       }
    }

    public Classified updateClassified(int classifiedId, String classifiedCategory, String classifiedTitle, String description,
		MultipartFile image) throws IOException {
    	Optional<Classified> exist = classifiedRepository.findById(classifiedId);
    	if(exist.isPresent()) {
			Classified update = exist.get();
			update.setClassifiedCategory(classifiedCategory);
			update.setClassifiedTitle(classifiedTitle);
			update.setDescription(description);
			update.setApproval(false);
			update.setClassifiedimage(Base64.getEncoder().encodeToString(image.getBytes()));
			return classifiedRepository.save(update);
    	}
    	return null; //throw record not found exception
    }
    
    public Classified updateClassified(int classifiedId, String classifiedCategory, String classifiedTitle, String description) throws IOException {
        Optional<Classified> exist = classifiedRepository.findById(classifiedId);
    	if(exist.isPresent()) {
			Classified update = exist.get();
			update.setClassifiedCategory(classifiedCategory);
			update.setClassifiedTitle(classifiedTitle);
			update.setDescription(description);
			update.setApproval(false);
			return classifiedRepository.save(update);
    	}
    	return null; //throw record not found exception
   }

    public void approve(int classifiedId) {
    	Optional<Classified> exist=classifiedRepository.findById(classifiedId);
		if(exist.isPresent()) {
			Classified update = exist.get();
			update.setApproval(true);
			classifiedRepository.save(update);
		}
	}
    
    public void reject(int classifiedId) {
        Optional<Classified> classified = classifiedRepository.findById(classifiedId);
        if(classified.isPresent()) {
            classifiedRepository.deleteById(classifiedId);
        }
        else {
            return; // replace with custom exception(RecordNotFoundException)
        }
    }
    
	public List<Classified> searchClassified(String searchText) {
		List<Classified> classified = new ArrayList<Classified>();
		classified.addAll(classifiedRepository.findByClassifiedTitleContaining(searchText));
		classified.addAll(classifiedRepository.findByDescriptionContaining(searchText));
		return filterSearched(classified);
    }
	
	public List<Classified> filterSearched(List<Classified> classified) {
		List<Classified> filtered = new ArrayList<Classified>();
		Set<Integer> set = new HashSet<Integer>();
		for(Classified i: classified) {
			if(set.add(i.getClassifiedId()) && i.isApproval()) {
				filtered.add(i);
			}
		}
		return filtered;
	}
}
