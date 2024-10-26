package com.dm.planetradehub.repository;

import com.dm.planetradehub.entity.Type;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface TypeRepository extends JpaRepository<Type, Long> {

}
