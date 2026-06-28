package ucr.ac.cr.MercadU.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.MercadU.model.dto.ProductRequestDTO;
import ucr.ac.cr.MercadU.model.dto.ProductResponseDTO;
import ucr.ac.cr.MercadU.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    //POST: Guardar producto
    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequestDTO requestDTO) {
        try {
            ProductResponseDTO newProduct = this.productService.saveProduct(requestDTO);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al guardar el producto: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //GET: Obtener todos
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAllProduct() {
        List<ProductResponseDTO> products = this.productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // GET: Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findByIDProduct(@PathVariable Integer id) {
        ProductResponseDTO product = this.productService.findByID(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado con el ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // GET: Obtener sólo disponibles (/api/product/disponibles)
    @GetMapping("/available")
    public ResponseEntity<List<ProductResponseDTO>> findAvailable() {
        List<ProductResponseDTO> available = this.productService.findByAvailable();
        return new ResponseEntity<>(available, HttpStatus.OK);
    }

}
