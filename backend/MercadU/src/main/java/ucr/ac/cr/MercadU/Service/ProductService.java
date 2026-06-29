package ucr.ac.cr.MercadU.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.MercadU.model.entity.Business;
import ucr.ac.cr.MercadU.model.entity.Product;
import ucr.ac.cr.MercadU.model.dto.ProductRequestDTO;
import ucr.ac.cr.MercadU.model.dto.ProductResponseDTO;
import ucr.ac.cr.MercadU.repository.BusinessRepository;
import ucr.ac.cr.MercadU.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BusinessRepository businessRepository;

    // Guardar un producto nuevo
    public ProductResponseDTO saveProduct(ProductRequestDTO requestDTO) {
        Business business = this.businessRepository.findById(requestDTO.getBusinessId())
                .orElseThrow(() -> new RuntimeException("El emprendimiento no existe"));

        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setDescription(requestDTO.getDescription());
        product.setPrice(requestDTO.getPrice());
        product.setAvailable(requestDTO.isAvailable());
        product.setBusiness(business);

        Product productS = this.productRepository.save(product);
        return this.convertToResponseDTO(productS);
    }

    // Obtener todos los productos
    public List<ProductResponseDTO> findAllProducts() {
        List<Product> listProductos = this.productRepository.findAll();
        return this.convertListDTO(listProductos);
    }

    // Obtener por ID
    public ProductResponseDTO findByID(Integer id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return this.convertToResponseDTO(optionalProduct.get());
        }
        return null;
    }

    // Obtener sólo los disponibles
    public List<ProductResponseDTO> findByAvailable() {
        List<Product> listaProductos = this.productRepository.findByAvailableTrue();
        return this.convertListDTO(listaProductos);
    }

    public List<ProductResponseDTO> findByBusiness(Integer businessId) {
        return this.convertListDTO(this.productRepository.findByBusinessId(businessId));
    }

    //Zona DTO
    private ProductResponseDTO convertToResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getIdProduct(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.isAvailable(),
                product.getBusiness().getId(),
                product.getBusiness().getName(),
                product.getBusiness().getOwner().getName()
        );
    }

    private List<ProductResponseDTO> convertListDTO(List<Product> productList) {
        List<ProductResponseDTO> listaDTO = new ArrayList<>();
        for (Product producto : productList) {
            listaDTO.add(this.convertToResponseDTO(producto));
        }
        return listaDTO;
    }
}
