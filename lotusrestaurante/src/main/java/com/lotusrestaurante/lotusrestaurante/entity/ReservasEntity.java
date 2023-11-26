package com.lotusrestaurante.lotusrestaurante.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "reserva")
public class ReservasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefonePrincipal;

    private String telefoneReserva;

    @NotBlank(message = "O data e hora é obrigatório")
    private String dataHoraReserva;

    @NotBlank(message = "O numero de pessoas é obrigatório")
    private Integer numeroPessoas;

    private String observacao;

}
