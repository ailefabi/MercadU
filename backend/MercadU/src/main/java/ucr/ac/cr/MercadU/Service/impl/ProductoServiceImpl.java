package ucr.ac.cr.MercadU.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.MercadU.Repository.ProductoRepository;
import ucr.ac.cr.MercadU.Service.ProductoService;
import ucr.ac.cr.MercadU.model.Producto;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public Producto crear(Producto producto) {

        validarProducto(producto);

        return productoRepository.save(producto);
    }


    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscar(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {

        validarProducto(producto);

        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());
        existente.setDisponible(producto.isDisponible());

        return productoRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }

    private void validarProducto(Producto producto) {

        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new RuntimeException("El nombre no puede estar vacío");
        }

        if (producto.getPrecio() <= 0) {
            throw new RuntimeException("El precio debe ser mayor a 0");
        }

        if (producto.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo");
        }
    }
}
