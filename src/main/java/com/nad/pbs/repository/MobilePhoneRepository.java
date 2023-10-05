package com.nad.pbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nad.pbs.entity.MobilePhone;

/**
 * Repository for {@link MobilePhone}
 * @author Nadeem
 *
 */

@Repository
public interface MobilePhoneRepository extends JpaRepository<MobilePhone, Integer>{
    
	@Query("SELECT m FROM MobilePhone m WHERE m.brand = :brand and m.model=:model")
    public List<MobilePhone> findMobilePhoneByBrandAndModel(String brand,String model);
}
