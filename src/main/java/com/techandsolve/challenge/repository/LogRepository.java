/**
 * 
 */
package com.techandsolve.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.techandsolve.challenge.model.Log;

/**
 * @author Jperezc
 *
 */
public interface LogRepository extends CrudRepository<Log, Long>  {

}
