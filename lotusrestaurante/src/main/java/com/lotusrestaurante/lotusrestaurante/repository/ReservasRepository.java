package com.lotusrestaurante.lotusrestaurante.repository;

import com.lotusrestaurante.lotusrestaurante.entity.ReservasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasRepository extends JpaRepository<ReservasEntity, Long> {
    // Pode adicionar métodos específicos de consulta, se necessário
}
