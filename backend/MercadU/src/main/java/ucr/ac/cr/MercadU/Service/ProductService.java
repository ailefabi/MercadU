package ucr.ac.cr.MercadU.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.MercadU.model.entity.Product;
import ucr.ac.cr.MercadU.model.dto.ProductRequestDTO;
import ucr.ac.cr.MercadU.model.dto.ProductResponseDTO;
import ucr.ac.cr.MercadU.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Guardar un producto nuevo
    public ProductResponseDTO guardar(ProductRequestDTO requestDTO) {
        Product producto = new Product();
        producto.setNombre(requestDTO.getNombre());
        producto.setDescripcion(requestDTO.getDescripcion());
        producto.setPrecio(requestDTO.getPrecio());
        producto.setDisponible(requestDTO.isDisponible());

        Product productoGuardado = this.productRepository.save(producto);
        return this.convertirADTO(productoGuardado);
    }

    // Obtener todos los productos
    public List<ProductResponseDTO> obtenerTodos() {
        List<Product> listaProductos = this.productRepository.findAll();
        return this.convertirListaDTO(listaProductos);
    }

    // Obtener por ID
    public ProductResponseDTO obtenerPorId(Integer id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return this.convertirADTO(optionalProduct.get());
        }
        return null;
    }

    // Obtener sólo los disponibles
    public List<ProductResponseDTO> obtenerDisponibles() {
        List<Product> listaProductos = this.productRepository.findByDisponibleTrue();
        return this.convertirListaDTO(listaProductos);
    }

    //Zona DTO
    private ProductResponseDTO convertirADTO(Product producto) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setDisponible(producto.isDisponible());
        return dto;
    }

    private List<ProductResponseDTO> convertirListaDTO(List<Product> listaProductos) {
        List<ProductResponseDTO> listaDTO = new ArrayList<>();
        for (Product producto : listaProductos) {
            listaDTO.add(this.convertirADTO(producto));
        }
        return listaDTO;
    }
}
