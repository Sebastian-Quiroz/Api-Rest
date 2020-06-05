/**
 * 
 */
package com.sebas.quiroz.quiroz.dao;

import org.springframework.data.repository.CrudRepository;

import com.sebas.quiroz.quiroz.model.Person;

/**
 * @author ADMIN
 *
 */
public interface IPersonDao extends CrudRepository<Person, Long> {

}
