package org.javaexercises.clienteservice.service;

import org.javaexercises.clienteservice.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    public List<Cliente> getAll();

    public Optional<Cliente> getById(Long id);

    public Cliente update(Long id, Cliente cliente);

    public Cliente save(Cliente cliente);

    public void delete(Long id);
}
