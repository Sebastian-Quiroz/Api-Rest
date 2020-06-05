/**
 * 
 */
package com.sebas.quiroz.quiroz.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * @author ADMIN
 *
 */
@Entity
public class Person implements Serializable {


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idperson")
    private Long idPerson;

    @Column(nullable = false)
    private String fullName;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birth;

    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}