package com.example.firstProj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.firstProj.donnees.*;

import com.example.firstProj.donnees.*;
@Repository
public interface OrdonnanceRepository extends JpaRepository <Ordonnance, Integer >  {
	
}
