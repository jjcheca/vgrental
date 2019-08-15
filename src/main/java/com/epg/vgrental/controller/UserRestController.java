package com.epg.vgrental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epg.vgrental.beans.User;
import com.epg.vgrental.service.UserService;
 
/**
 * @author Juan José Checa Mora
 *
 */
@RestController
public class UserRestController {
  
	 @Autowired
	 private UserService userService;
	  
	 public void setUserService(UserService userService) {
	  
		 this.userService = userService;
		 
	 }
	 
	 @GetMapping("/api/users")
	 public List<User> getUsers() {
	  
		 List<User> users = userService.retrieveUsers();
		 
		 return users;
	 }
	  
	 @GetMapping("/api/users/{userId}")
	 public User getUser(@PathVariable(name="userId")Long userId) {
		 
		 return userService.getUser(userId);
		 
	 }
	  
	 @PostMapping("/api/users")
	 public void saveUser(@RequestBody User user){
		 
		  userService.saveUser(user);
		  System.out.println("User Saved Successfully");
		  
	 }
	  
	 @DeleteMapping("/api/users/{userId}")
	 public void deleteUser(@PathVariable(name="UserId")Long userId) {
		 
		  userService.deleteUser(userId);
		  System.out.println("User Deleted Successfully");
		  
	 }
	  
	 @PutMapping("/api/users/{userId}")
	 public User updateUser(@RequestBody User user, @PathVariable(name="UserId")Long userId) {
		 
		  User emp = userService.getUser(userId);
		  
		  if(emp != null){
			  user.setUserId(userId);
			  return userService.saveUser(user);
		  }
		  return null;
		   
	 }
 
}
