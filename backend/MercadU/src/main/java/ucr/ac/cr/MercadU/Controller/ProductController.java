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
    public ResponseEntity<?> crearProducto(@RequestBody ProductRequestDTO requestDTO) {
        try {
            ProductResponseDTO nuevoProducto = this.productService.guardar(requestDTO);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al guardar el producto: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //GET: Obtener todos
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> obtenerTodosLosProductos() {
        List<ProductResponseDTO> productos = this.productService.obtenerTodos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // GET: Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Integer id) {
        ProductResponseDTO producto = this.productService.obtenerPorId(id);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado con el ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // GET: Obtener sólo disponibles (/api/product/disponibles)
    @GetMapping("/disponibles")
    public ResponseEntity<List<ProductResponseDTO>> obtenerProductosDisponibles() {
        List<ProductResponseDTO> disponibles = this.productService.obtenerDisponibles();
        return new ResponseEntity<>(disponibles, HttpStatus.OK);
    }

}
