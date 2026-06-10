package ucr.ac.cr.MercadU.Service;

import ucr.ac.cr.MercadU.model.DTO.ProductoDTO;
import ucr.ac.cr.MercadU.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto crear(ProductoDTO dto);

    List<Producto> listar();

    Producto buscar(Long id);

    Producto actualizar(Long id, ProductoDTO dto);

    void eliminar(Long id);
}