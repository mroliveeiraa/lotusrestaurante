package com.lotusrestaurante.lotusrestaurante.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ReservaDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefonePrincipal;

    private String telefoneReserva;

    @NotBlank(message = "A data e hora são obrigatórias")
    private String dataHoraReserva;

    @NotNull(message = "O número de pessoas é obrigatório")
    @Positive(message = "O número de pessoas deve ser maior que zero")
    private Integer numeroPessoas;

    private String observacao;
}
