package design.patterns.spring.service;


import design.patterns.spring.dtos.ClienteRequestDTO;
import design.patterns.spring.model.Cliente;

import java.util.UUID;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 *
 * @author falvojr
 */
public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(UUID id);

    Cliente inserir(ClienteRequestDTO clienteRequestDTO);

    void atualizar(UUID id, Cliente cliente);

    void deletar(UUID id);

}