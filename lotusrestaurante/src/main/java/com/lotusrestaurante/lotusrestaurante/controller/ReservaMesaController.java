package com.lotusrestaurante.lotusrestaurante.controller;


import com.lotusrestaurante.lotusrestaurante.entity.ReservasEntity;
import com.lotusrestaurante.lotusrestaurante.service.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5500") // Substitua pelo endereço do seu frontend
@RestController
@RequestMapping("/reservamesa")
public class ReservaMesaController {

    private final ReservasService reservasService;

    @Autowired
    public ReservaMesaController(ReservasService reservasService) {
        this.reservasService = reservasService;
    }

    @PostMapping
    public ResponseEntity<ReservasEntity> criarReserva(@Valid @RequestBody ReservasEntity reserva) {
        ReservasEntity reservaSalva = reservasService.salvarReserva(reserva);
        return new ResponseEntity<>(reservaSalva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarReserva(
            @PathVariable Long id,
            @Valid @RequestBody ReservasEntity reservaAtualizada
    ) {
        // Verificar se a reserva existe
        Optional<ReservasEntity> optionalReserva = reservasService.findById(id);
        if (optionalReserva.isPresent()) {
            ReservasEntity reservaExistente = optionalReserva.get();

            // Atualizar os dados da reserva existente com os dados da reserva atualizada
            reservaExistente.setNome(reservaAtualizada.getNome());
            reservaExistente.setEmail(reservaAtualizada.getEmail());
            reservaExistente.setTelefonePrincipal(reservaAtualizada.getTelefonePrincipal());
            reservaExistente.setTelefoneReserva(reservaAtualizada.getTelefoneReserva());
            reservaExistente.setDataHoraReserva(reservaAtualizada.getDataHoraReserva());
            reservaExistente.setNumeroPessoas(reservaAtualizada.getNumeroPessoas());
            reservaExistente.setObservacao(reservaAtualizada.getObservacao());

            // Salvar a reserva atualizada no banco de dados
            ReservasEntity reservaSalva = reservasService.save(reservaExistente);

            return ResponseEntity.ok(reservaSalva);
        } else {
            // Se a reserva não foi encontrada, retornar uma mensagem adequada
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reserva não encontrada com o ID: " + id);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> consultarReserva(@PathVariable Long id) {
        try {
            // Chama o serviço para encontrar a reserva com o ID fornecido
            Optional<ReservasEntity> reservaOptional = reservasService.findById(id);

            // Se a reserva foi encontrada, retorna um ResponseEntity com status OK (HTTP 200)
            return reservaOptional.<ResponseEntity<Object>>map(ResponseEntity::ok)
                    // Se não houver reserva, retorna um ResponseEntity com status NOT_FOUND (HTTP 404)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Reserva não encontrada com o ID: " + id));
        } catch (Exception ex) {

            // Se ocorrer uma exceção durante o processamento, retorna um ResponseEntity com status INTERNAL_SERVER_ERROR (HTTP 500)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao processar a solicitação.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirReserva(@PathVariable Long id) {
        try {
            // Verifica se a reserva com o ID fornecido existe antes de tentar excluí-la
            if (reservasService.existsById(id)) {
                // Se a reserva existe, chama o serviço para excluí-la
                reservasService.deleteById(id);

                // Retorna um ResponseEntity com status NO_CONTENT (HTTP 204) indicando sucesso na exclusão
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                // Se a reserva não foi encontrada, retorna um ResponseEntity com status NOT_FOUND (HTTP 404)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Reserva não encontrada com o ID: " + id);
            }
        } catch (Exception ex) {
            // Se ocorrer uma exceção durante o processamento, retorna um ResponseEntity com status INTERNAL_SERVER_ERROR (HTTP 500)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao processar a solicitação de exclusão.");
        }
    }
}
