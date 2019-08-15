package com.epg.vgrental.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epg.vgrental.beans.User;
 
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
 
}
