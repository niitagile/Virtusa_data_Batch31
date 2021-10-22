package com.springBoot.ConnectGlobe;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class GlobeService implements UserDetailsService{
	
	@Autowired
	GlobeRepo repo;
	
	@Autowired
	UserRepo repop;
	
	@Autowired
	uploadEntityRepo repou;
	
	@Autowired
	commentRepo repoc;
	
	@Autowired
	suggestEntityRepo repos;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MyReportEntityRepo myre;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CredentialModel model = repo.findByUsername(username);
		if(model==null) {
			throw new UsernameNotFoundException("Not Found...");
		}
		return new GlobeGetDetails(model);
	}
	public UserModel saveToUser(UserModel model) {
		System.out.println(model.getPassword());
		model.setPassword(passwordEncoder.encode(model.getPassword()));
		UserModel u=repop.save(model);
		return u;
	}
	public CredentialModel saveToCredential(CredentialModel model) {
		model.setPassword(passwordEncoder.encode(model.getPassword()));
		CredentialModel c=repo.save(model);
		return c;
	}
	public CredentialModel findByUsername(String username) {
		CredentialModel model=repo.findByUsername(username);
		return model;
	}
	public UserModel findByEmail(String username) {
		UserModel p=repop.findByEmail(username);
		return p;
	}
	public uploadEntity saveToMyPosts(uploadEntity u) {
		uploadEntity t=repou.save(u);
		return t;
	}
	public List<imageEntityClass> getAllPosts() throws UnsupportedEncodingException{
		String image="";
		List<imageEntityClass> t=new ArrayList<>();
		List<uploadEntity> l=repou.findAll();
		for(int i=0;i<l.size();i++) {
			if(l.get(i).getImage() != null) {
				byte[] encode = java.util.Base64.getEncoder().encode(l.get(i).getImage());
				image =new String(encode,"UTF-8");
				
			}
			String tag=l.get(i).getTag();
			int pId=l.get(i).getpId();
			int userId=l.get(i).getUserId();
			UserModel q=repop.getById(userId);
			String name=q.getFullname();
			imageEntityClass k=new imageEntityClass(pId,userId,tag,image,name);
			System.out.println(k.toString());
			t.add(k);
		}
		System.out.println(t);
		return t;
	}
	public List<MyReportEntityClass> getAllReports()
	{
		List<MyReportEntityClass> l = new ArrayList<>();
		List<MyReportEntity> m = myre.findAll();
		for(int i=0;i<m.size();i++)
		{
			int rid= m.get(i).getrId();
			String issue=m.get(i).getIssue();
			int uid=m.get(i).getUserId();
			UserModel q=repop.getById(uid);
			String name=q.getFullname();
			MyReportEntityClass m1 = new MyReportEntityClass(rid, uid, issue, name);
			System.out.println(m1.toString());
			l.add(m1);
		}
		return l;
	}
	public List<imageEntityClass> getMyPosts(int id) throws UnsupportedEncodingException{
		System.out.println("inside");
		String image="";
		List<imageEntityClass> t=new ArrayList<>();
		List<uploadEntity> l=repou.findByUserId(id);
		System.out.println("outside"+l.size());
		for(int i=0;i<l.size();i++) {
			if(l.get(i).getImage() != null) {
				byte[] encode = java.util.Base64.getEncoder().encode(l.get(i).getImage());
				image =new String(encode,"UTF-8");				
			}
			String tag=l.get(i).getTag();
			int pId=l.get(i).getpId();
			int userId=l.get(i).getUserId();
			UserModel q=repop.getById(userId);
			String name=q.getFullname();
			imageEntityClass k=new imageEntityClass(pId,userId,tag,image,name);
			System.out.println(k.toString());
			t.add(k);
		}
		System.out.println(t);
		return t;
	}
	public MyReportEntity saveToMyReport(MyReportEntity my) {
		// TODO Auto-generated method stub
		MyReportEntity r =myre.save(my);
		return r;
	}
	public List<MyReportEntityClass> getMyReports(int uid) {
		System.out.println("inside getMyReports");
		List<MyReportEntityClass> l = new ArrayList<>();
		List<MyReportEntity> m = myre.findByUserId(uid);
		System.out.println("outside"+l.size());
		for(int i=0;i<m.size();i++)
		{
			int rid= m.get(i).getrId();
			String issue=m.get(i).getIssue();
			UserModel q=repop.getById(uid);
			String name=q.getFullname();
			MyReportEntityClass m1 = new MyReportEntityClass(rid, uid, issue, name);
			System.out.println(m1.toString());
			l.add(m1);
		}
		return l;
				
	}
	public commentEntity saveToComment(commentEntity u) {
		commentEntity t=repoc.save(u);
		return t;
	}
	public List<commentEntityClass> viewComments(int id) throws UnsupportedEncodingException{
		int uId;
		List<commentEntityClass> li=new ArrayList<>();
		List<commentEntity> l=repoc.findBypId(id);
		System.out.println(l.size()+"list size....");
		for(int i=0;i<l.size();i++) {
			uId=l.get(i).getUserId();
			UserModel y=repop.getById(uId);
			String name=y.getFullname();
			String comment=l.get(i).getComment();
			int pId=l.get(i).getpId();
			int cId=l.get(i).getcId();
			commentEntityClass t=new commentEntityClass(cId,pId,uId,comment,name);
			li.add(t);
		}
		return li;
	}
	public List<imageEntityClass> getMyPostsComment(int id) throws UnsupportedEncodingException{
		System.out.println("inside");
		String image="";
		List<imageEntityClass> t=new ArrayList<>();
		uploadEntity d=repou.getById(id);
		System.out.println("outside");
		if(d.getImage() != null) {
			byte[] encode = java.util.Base64.getEncoder().encode(d.getImage());
			image =new String(encode,"UTF-8");				
		}
		String tag=d.getTag();
		int pId=d.getpId();
		int userId=d.getUserId();
		UserModel q=repop.getById(userId);
		String name=q.getFullname();
		imageEntityClass k=new imageEntityClass(pId,userId,tag,image,name);
		System.out.println(k.toString());
		t.add(k);
		System.out.println(t);
		return t;
	}
	public suggestEntity saveToSuggestions(suggestEntity s) {
		suggestEntity d=repos.save(s);
		return d;
	}
	public List<suggestionClass> viewSuggestions(int id){
		List<suggestionClass> li=new ArrayList<>();
		List<suggestEntity> l=repos.findByrId(id);
		for(int i=0;i<l.size();i++) {
			int userId=l.get(i).getUserId();
			UserModel q=repop.getById(userId);
			String name=q.getFullname();
			String suggest=l.get(i).getSuggest();
			suggestionClass s=new suggestionClass(id,suggest,name);
			li.add(s);
		}
		return li;
	}
	public MyReportEntity getMyReportSuggest(int id) {
		MyReportEntity m=myre.getById(id);
		return m;
		
	}
	public int deleteInSuggestions(int id) {
		repos.deleteByrId(id);
		return 1;
	}
	public int deleteInReports(int id) {
		myre.deleteById(id);
		return 1;
	}
	public int deleteInComments(int id) {
		repoc.deleteBypId(id);
		return 1;
	}
	public int deleteInPost(int id) {
		repou.deleteById(id);
		return 1;
	}
	public List<UserModel> getProfileDetails(int Uid)
	{
		List<UserModel> l= new ArrayList<>();
		UserModel q=repop.getById(Uid);
		l.add(q);
		return l;
	}
	public UserModel getDetails(int id) {
		UserModel u=repop.getById(id);
		return u;
	}
	public CredentialModel getCredential(int id) {
		CredentialModel c=repo.getById(id);
		return c;
	}
	public List<UserModel> getAllUsers() {
		List<UserModel> l=repop.findAll();
		return l;
	}
	public int deleteUser(int id) {
		repop.deleteById(id);
		return 1;
	}
	public boolean checkemailexists(String email) {
        UserModel u = repop.findByEmail(email);
        if(u != null) {
        	return true;
        }
        else {
        	return false;
        }
	}
}
