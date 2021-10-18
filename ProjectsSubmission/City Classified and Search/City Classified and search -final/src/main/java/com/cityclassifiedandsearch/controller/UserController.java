package com.cityclassifiedandsearch.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cityclassifiedandsearch.bean.ConfirmationToken;
import com.cityclassifiedandsearch.bean.User;
import com.cityclassifiedandsearch.repo.ConfirmationTokenRepository;
import com.cityclassifiedandsearch.repo.UserRepository;
import com.cityclassifiedandsearch.service.EmailService;
import com.cityclassifiedandsearch.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private UserRepository userRepository;
	
	//@Autowired
	//private EmailController email;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String userLoginForm() {
		return "login";
		
	}
	
	@GetMapping("/register")
	public String userRegistrationForm() {
		//email.approvalMail(1);
		
		return "register";
	}
	
	@PostMapping("/register")
	public String userRegistration(@ModelAttribute("user") User user) throws MessagingException {
		
		if(userRepository.findByUserEmail(user.getUserEmail())==null){
			userService.save(user);
			
	
		ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);        
        
        emailService.send(user.getUserEmail(),"Complete Registration!", "To confirm your account, please click here : "+"http://localhost:8081/confirm-account?token="+confirmationToken.getConfirmationToken());

        return "login";
		}else { 
			return "redirect:/register?registration-status=failed";
		}
	}
	
	@RequestMapping(value="/confirm-account",method= {RequestMethod.GET, RequestMethod.POST})
	
	
	public String confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
        	User user = userRepository.findByUserEmail(token.getUserEntity().getUserEmail());
            user.setEnabled('y');          
            
            
            
            userRepository.save(user);//http://localhost:8081/confirm-account?token=24d2c910-f487-4e3b-bc0d-44ce34beb009
          //  modelAndView.setViewName("accountVerified");
            	System.out.println("Success");
            return "ConfirmationSuccess";
        }
        else
        {
        	System.out.println("Failure");
            return "Error,The link is invalid or broken!";
            
        }

      
    }
}