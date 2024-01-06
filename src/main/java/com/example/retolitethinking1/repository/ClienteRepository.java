package com.example.retolitethinking1.repository;

import com.example.retolitethinking1.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository  extends JpaRepository<ClienteModel,Long> {

    Optional<ClienteModel> findByCedulaCiudadania(String cedula);
}
