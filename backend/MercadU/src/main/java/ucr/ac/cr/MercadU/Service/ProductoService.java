package ucr.ac.cr.MercadU.Service;
import ucr.ac.cr.MercadU.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto crear(Producto producto);

    List<Producto> listar();

    Producto buscar(Long id);

    Producto actualizar(Long id, Producto producto);

    void eliminar(Long id);
}