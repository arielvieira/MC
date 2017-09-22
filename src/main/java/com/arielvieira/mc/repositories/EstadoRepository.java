package com.arielvieira.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arielvieira.mc.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
}
