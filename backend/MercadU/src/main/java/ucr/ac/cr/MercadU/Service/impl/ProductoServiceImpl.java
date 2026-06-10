package ucr.ac.cr.MercadU.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.MercadU.Repository.ProductoRepository;
import ucr.ac.cr.MercadU.Service.ProductoService;
import ucr.ac.cr.MercadU.model.DTO.ProductoDTO;
import ucr.ac.cr.MercadU.model.Producto;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto crear(ProductoDTO dto) {

        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        p.setDisponible(dto.isDisponible());

        return productoRepository.save(p);
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
    public Producto actualizar(Long id, ProductoDTO dto) {

        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        p.setDisponible(dto.isDisponible());

        return productoRepository.save(p);
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}