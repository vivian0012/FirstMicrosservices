package com.marcasMedicamentos.brands.repositories;

import com.marcasMedicamentos.brands.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicamento, Long> {
}
