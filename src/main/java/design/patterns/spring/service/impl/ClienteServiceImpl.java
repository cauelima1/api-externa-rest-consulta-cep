package design.patterns.spring.service.impl;


import design.patterns.spring.dtos.ClienteRequestDTO;
import design.patterns.spring.model.Cliente;
import design.patterns.spring.model.ClienteRepository;
import design.patterns.spring.model.Endereco;
import design.patterns.spring.model.EnderecoRepository;
import design.patterns.spring.service.ClienteService;
import design.patterns.spring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 *
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    // Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.



    @Override
    public Iterable<Cliente> buscarTodos() {
        // Buscar todos os Clientes.
        return clienteRepository.findAll();
    }



    @Override
    public Cliente buscarPorId(UUID id) {
        // Buscar Cliente por ID.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public Cliente inserir(ClienteRequestDTO clienteRequestDTO) {
      Cliente cliente = new Cliente();
      cliente.setNome(clienteRequestDTO.getNome());
      cliente.setEndereco(recuperarEnderecoComCep(clienteRequestDTO.getCep()));
      cliente.setId(cliente.getId());
      clienteRepository.save(cliente);
     return cliente;
    }

    @Override
    public void atualizar(UUID id, Cliente cliente) {
        // Buscar Cliente por ID, caso exista:
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(UUID id) {
        // Deletar Cliente por ID.
        clienteRepository.deleteById(id);
    }

    private Endereco recuperarEnderecoComCep(String cep) {
       return enderecoRepository.findById(cep).orElseGet(() ->{
            Endereco novoEndereco = viaCepService.consultarCep(String.valueOf(cep));
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        enderecoRepository.findById(cep).orElseGet(()->{
            Endereco novoEndereco = viaCepService.consultarCep(String.valueOf(cep));
            enderecoRepository.save(novoEndereco);
            cliente.setEndereco(novoEndereco);
            cliente.setId(cliente.getId());
            return novoEndereco;
        });
        cliente.setId(cliente.getId());
        clienteRepository.save(cliente);
    }


}