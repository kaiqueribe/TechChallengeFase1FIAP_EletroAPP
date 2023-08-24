package com.fiap.grupo9.AppEletroControl.dominio.endereco.controller;

import com.fiap.grupo9.AppEletroControl.dominio.endereco.dto.EnderecoDTO;

import com.fiap.grupo9.AppEletroControl.dominio.endereco.service.EnderecoService;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/enderecos")
@Slf4j
@AllArgsConstructor
public class EnderecoController {

    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<Page<EnderecoDTO>> buscarTodos(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho
    ) {
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);

        var enderecos = enderecoService.buscarTodos(pageRequest);
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarPorId(@PathVariable Long id) {
        var pessoa = enderecoService.buscarPorId((id));
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> salvar(@RequestBody EnderecoDTO enderecoDTO) {
        var enderecoCadastrado = enderecoService.cadastrar(enderecoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((enderecoCadastrado.getId())).toUri();
        return ResponseEntity.created(uri).body(enderecoCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizar(@RequestBody EnderecoDTO enderecoDTO, @PathVariable Long id) {
        var enderecoAtualizado = enderecoService.atualizar(id, enderecoDTO);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        enderecoService.remover(id);
        return ResponseEntity.noContent().build();
    }


}
