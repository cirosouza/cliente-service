package org.javaexercises.clienteservice.service.implementation;

import org.javaexercises.clienteservice.exception.ResourceNotFoundException;
import org.javaexercises.clienteservice.model.Cliente;
import org.javaexercises.clienteservice.repository.ClienteRepository;
import org.javaexercises.clienteservice.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImplementation implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> getById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException("Não encontrado o cliente com id: " + id);
        }
        return cliente;
    }

    @Override
    public Cliente update(Long id, Cliente cliente) {
        Optional<Cliente> clienteOptional = getById(id);

        if (clienteOptional.isEmpty()) {
            throw new ResourceNotFoundException("Não encontrado cliente com id " + id);
        }

        Cliente existingCliente = clienteOptional.get();
        existingCliente.setNome(cliente.getNome());
        existingCliente.setEndereco(cliente.getEndereco());
        existingCliente.setEmail(cliente.getEmail());
        existingCliente.setIdade(cliente.getIdade());

        return clienteRepository.save(existingCliente);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void delete(Long id) {
        Optional<Cliente> cliente = getById(id);

        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException("Não encontrado cliente com id " + id);
        }
        clienteRepository.delete(cliente.get() );
    }
}
