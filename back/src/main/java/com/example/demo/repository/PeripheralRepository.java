package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitys.Peripheral;

public interface PeripheralRepository extends JpaRepository<Peripheral, Long>{

}