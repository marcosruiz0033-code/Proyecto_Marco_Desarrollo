package com.PC.Store.SistemaWeb.repository;

import com.PC.Store.SistemaWeb.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository <Producto,Integer> {
}
