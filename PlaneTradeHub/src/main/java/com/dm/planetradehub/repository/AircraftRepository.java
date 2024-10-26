package com.dm.planetradehub.repository;


import com.dm.planetradehub.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

}
