package com.pharmacy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;
import com.pharmacy.payload.ItemPayload;
import com.pharmacy.service.DistributorItemService;
import com.pharmacy.service.ItemsService;

@RestController
public class ItemsController {

	@Autowired
	private ItemsService itemsService;

	@Autowired
	private DistributorItemService distributorService;

	@PostMapping("/additem")
	@PreAuthorize("hasAnyRole('DISTRIBUTOR','ADMIN')")
	public ModelAndView addItem(@ModelAttribute("item") ItemPayload item, @RequestParam("username") String distributor,
			Model model) {
		ItemsBean itemBean = new ItemsBean();
		if(item.getDistributor()==null) {
		itemBean.setDistributor(distributor);}
		else {
			itemBean.setDistributor(item.getDistributor());
		}
		itemBean.setCategory(item.getCategory());
		DistributorItemBean distributorItem = new DistributorItemBean();
		distributorItem.setItemBean(itemBean);
		distributorItem.setDescription(item.getDescription());
		distributorItem.setItemName(item.getItemName());
		distributorItem.setPrice(item.getPrice());
		distributorItem.setQuantity(item.getQuantity());
		itemsService.addItem(itemBean, distributorItem);
		model.addAttribute("item", new ItemPayload());
		model.addAttribute("message", "SUCCESS");
		return new ModelAndView("items");
	}

	@GetMapping("/getitems")
	@PreAuthorize("hasAnyRole('DISTRIBUTOR','ADMIN')")
	public ModelAndView getItems(@RequestParam("username") String distributor, Model model,
			HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("token");
		List<DistributorItemBean> distributorItem = new ArrayList<>();
		if (token.contains("ADMIN")) {
			distributorItem = distributorService.getAllItems();
		} else {
			distributorItem = distributorService.getAllItemsByItemID(distributor);
		}
		model.addAttribute("items", distributorItem);
		return new ModelAndView("itemstable");
	}

	@GetMapping("/getdistributoritems")
	@PreAuthorize("hasRole('USER')")
	public ModelAndView getProducts(@RequestParam("category") String category,
			@RequestParam("distributor") String distributor, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (category != null && distributor != null) {
			session.setAttribute("category", category);
			session.setAttribute("distributor", distributor);
		} else {
			category = (String) session.getAttribute("category");
			distributor = (String) session.getAttribute("distributor");
		}
		List<DistributorItemBean> items = itemsService.getDistributorItems(distributor);
		if (items != null && !items.isEmpty()) {
			session.setAttribute("distributoritems", items);
		} else {
			session.setAttribute("message", "FAILED");
		}
		return new ModelAndView("itemslist");
	}

	@GetMapping("/edititem")
	@PreAuthorize("hasAnyRole('DISTRIBUTOR','ADMIN')")
	public ModelAndView editItem(@RequestParam("id") int id, Model model) {
		DistributorItemBean distributorItem = distributorService.getItemById(id);
		model.addAttribute("item", distributorItem);
		return new ModelAndView("edititem");
	}

	@PostMapping("/edititem")
	@PreAuthorize("hasAnyRole('DISTRIBUTOR','ADMIN')")
	public ModelAndView editItem(@RequestParam("itemId") int itemId,
			@ModelAttribute("item") DistributorItemBean distributorItem, Model model) {
		ItemsBean item = itemsService.getItemById(itemId);
		distributorItem.setItemBean(item);
		distributorService.updateDistributorItemById(distributorItem);
		model.addAttribute("item", new ItemPayload());
		model.addAttribute("message", "SUCCESS");
		return new ModelAndView("items");
	}

	
	@GetMapping("deleteitem/{id}")
	@PreAuthorize("hasAnyRole('DISTRIBUTOR','ADMIN')")
	public ModelAndView deleteItem(@PathVariable int id, Model model) {
		distributorService.deleteItem(id);
		model.addAttribute("message", "SUCCESS");
		model.addAttribute("item", new ItemPayload());
		return new ModelAndView("items");

	}

}
