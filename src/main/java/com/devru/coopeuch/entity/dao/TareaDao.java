package com.devru.coopeuch.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devru.coopeuch.entity.models.Tarea;

@Repository
public interface TareaDao extends JpaRepository<Tarea, Long> {}
