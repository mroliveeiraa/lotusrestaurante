package com.lotusrestaurante.lotusrestaurante.service;

import com.lotusrestaurante.lotusrestaurante.entity.ReservasEntity;
import com.lotusrestaurante.lotusrestaurante.repository.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservasService {

    private final ReservasRepository reservasRepository;

    @Autowired
    public ReservasService(ReservasRepository reservasRepository) {
        this.reservasRepository = reservasRepository;
    }

    public ReservasEntity  salvarReserva(ReservasEntity reserva) {
        return reservasRepository.save(reserva);
    }

    public Optional<ReservasEntity> findById(Long id) {
        Optional<ReservasEntity> reservaExistente = reservasRepository.findById(id);

        return reservaExistente;
    }

    public ReservasEntity save(ReservasEntity reservaExistente) {
        return reservasRepository.save(reservaExistente);
    }

    public boolean existsById(Long id) {
        return reservasRepository.existsById(id);
    }

    public void deleteById(Long id) {
        reservasRepository.deleteById(id);
    }

    // Adicione métodos adicionais conforme necessário para buscar, atualizar, excluir reservas, etc.
}