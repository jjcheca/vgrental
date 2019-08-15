package com.epg.vgrental.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epg.vgrental.beans.User;
import com.epg.vgrental.repository.UserRepository;
import com.epg.vgrental.service.UserService;
 
/**
 * @author Juan José Checa Mora
 *
 */
@Service
public class UserServiceImpl implements UserService {
 
	 @Autowired
	 private UserRepository userRepository;
	 
	 public void setUserRepository(UserRepository userRepository) {
		 
		 this.userRepository = userRepository;
		 
	 }
	  
	 public List<User> retrieveUsers() {
		 
		 List<User> users = userRepository.findAll();
		 
		 return users;
		 
	 }
	  
	 public User getUser(Long userId) {
		 
		 Optional<User> user = userRepository.findById(userId);
		 
		 return user.isPresent() ? user.get() : null;
		 
	 }
	  
	 public User saveUser(User user){
		 
		 return userRepository.save(user);
		 
	 }
	  
	 public void deleteUser(Long userId){
		 
		 userRepository.deleteById(userId);
		 
	 }
 
}
