/**
 * 
 */
package com.sebas.quiroz.quiroz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sebas.quiroz.quiroz.dao.IPersonDao;
import com.sebas.quiroz.quiroz.model.Person;

/**
 * @author ADMIN
 *
 */
public class PersonService {
	
	@Autowired
	private IPersonDao dao;
	
	@Transactional(readOnly = true)
	public List<Person> list() { 
    	return  (List<Person>) dao.findAll(); 
    }
	
	public Person personId(long id) {
        return  dao.findById(id).orElse(null);
    }
	
	public Person save(Person p) {
		return dao.save(p);
	}
	
	public Person update(Person p) { 
		return dao.save(p); 
	}

    public void delete(Person p) { 
    	dao.delete(p); 
    }


}
