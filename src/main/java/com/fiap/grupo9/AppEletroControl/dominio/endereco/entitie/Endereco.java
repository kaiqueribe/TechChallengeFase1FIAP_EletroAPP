package com.fiap.grupo9.AppEletroControl.dominio.endereco.entitie;


import lombok.*;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;


@Entity
@Table(name = "tb_endereco")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonIgnore // Ignorar o campo "id" na serialização no método POST
    private Long id;
    //    @JsonProperty
//    @NotBlank
//    @Size( max = 255,message = "Numero de Catacteres Excedido" )
    private String rua;
    //    @JsonProperty
//    @NotBlank
//    @Pattern(regexp = "\\d{8}", message = "CEP inválido")
    private String cep;
    //    @JsonProperty
//    @Range(min = 0, max = 10000)
    private String numero;
    //    @JsonProperty
//    @NotBlank
    private String bairro;
    //    @JsonProperty
//    @NotBlank
//    @Size(max = 100)
    private String cidade;
    //    @JsonProperty
//    @NotBlank
//    @Size(max = 2)
    private String uf;
    //    @JsonProperty
    private String complemento;
}
