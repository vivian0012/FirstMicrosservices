package com.marcasMedicamentos.brands.repositories;

import com.marcasMedicamentos.brands.medicamentoDTO.DTOs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DTOsRepository extends JpaRepository<DTOs, Long> {
}
