package com.mundoMascota.service;
import com.mundoMascota.model.Cliente;
import com.mundoMascota.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ClienteService {
    @Autowired private ClienteRepository clienteRepository;
    public List<Cliente> listarTodos() { return clienteRepository.findAll(); }
    public Optional<Cliente> buscarPorId(Long id) { return clienteRepository.findById(id); }
    public Cliente guardar(Cliente cliente) { return clienteRepository.save(cliente); }
    public Cliente actualizar(Long id, Cliente c) {
        return clienteRepository.findById(id).map(x -> {
            x.setNombre(c.getNombre()); x.setApellido(c.getApellido()); x.setTelefono(c.getTelefono());
            x.setEmail(c.getEmail()); x.setDni(c.getDni()); x.setDireccion(c.getDireccion());
            return clienteRepository.save(x);
        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + id));
    }
    public void eliminar(Long id) { clienteRepository.deleteById(id); }
}
