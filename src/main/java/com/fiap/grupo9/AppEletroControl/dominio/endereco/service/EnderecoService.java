package com.fiap.grupo9.AppEletroControl.dominio.endereco.service;

import com.fiap.grupo9.AppEletroControl.dominio.eletrodomestico.service.exception.ControllerNotFoundException;
import com.fiap.grupo9.AppEletroControl.dominio.eletrodomestico.service.exception.DatabaseException;
import com.fiap.grupo9.AppEletroControl.dominio.endereco.dto.EnderecoDTO;
import com.fiap.grupo9.AppEletroControl.dominio.endereco.entitie.Endereco;
import com.fiap.grupo9.AppEletroControl.dominio.endereco.mapper.EnderecoMapper;
import com.fiap.grupo9.AppEletroControl.dominio.endereco.repository.IEnderecoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EnderecoService {

    private IEnderecoRepository repository;
    private EnderecoMapper enderecoMapper;

    public Page<EnderecoDTO> buscarTodos(PageRequest pagina) {
        log.info("Buscando todos os endereços...");
        var enderecos = repository.findAll(pagina);
        return enderecos.map(enderecoMapper::toDTO);
    }

    public EnderecoDTO buscarPorId(Long id) {
        log.info("Buscando endereço com id {}...", id);
        var endereco = repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Endereço Não Encontrado"));
        return enderecoMapper.toDTO(endereco);
    }

    public EnderecoDTO cadastrar(EnderecoDTO enderecoDTO) {
        log.info("Cadastrando endereço {}...", enderecoDTO);
        Endereco endereco = enderecoMapper.toEntity(enderecoDTO);
        var enderecoCadastrado = repository.save(endereco);
        return enderecoMapper.toDTO(enderecoCadastrado);
    }

    public EnderecoDTO atualizar(Long id, EnderecoDTO enderecoDTO) {
        log.info("Atualizando endereço {}...", enderecoDTO);
        Endereco enderecoBuscado = repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Endereço não encontrado"));
        enderecoBuscado.setRua(enderecoDTO.getRua());
        enderecoBuscado.setCep(enderecoDTO.getCep());
        enderecoBuscado.setNumero(enderecoDTO.getNumero());
        enderecoBuscado.setBairro(enderecoDTO.getBairro());
        enderecoBuscado.setCidade(enderecoDTO.getCidade());
        enderecoBuscado.setUf(enderecoDTO.getUf());
        enderecoBuscado.setComplemento(enderecoDTO.getComplemento());
        enderecoBuscado = repository.save(enderecoBuscado);

        return enderecoMapper.toDTO(enderecoBuscado);

    }

    public void remover(Long id){
        log.info("Removendo endereço {}...");
        try{
            repository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new EntityNotFoundException("Eletrodoméstico não encontracdo"+id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade da base");
        }
    }

}
