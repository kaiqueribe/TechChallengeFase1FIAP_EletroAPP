package com.fiap.grupo9.AppEletroControl.dominio.endereco.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EnderecoDTO {

    private Long id;
    private String rua;
    private String cep;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;

}
