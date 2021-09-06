package com.example.firstProj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.firstProj.donnees.*;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

}
