package com.epg.vgrental.service;
 
import java.util.List;

import com.epg.vgrental.beans.User;	 
	 
/**
 * @author Juan José Checa Mora
 *
 */
public interface UserService {
	
	  List<User> retrieveUsers();
	  
	  User getUser(Long userId);
	  
	  User saveUser(User user);
	  
	  void deleteUser(Long userId);
}
