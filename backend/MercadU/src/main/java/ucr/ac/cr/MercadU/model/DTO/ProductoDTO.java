package ucr.ac.cr.MercadU.model.DTO;

import jakarta.validation.constraints.*;

public class ProductoDTO {

    @NotBlank
    private String nombre;

    private String descripcion;

    @NotNull
    @Positive
    private Double precio;

    @NotNull
    @PositiveOrZero
    private Integer stock;

    private boolean disponible;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}