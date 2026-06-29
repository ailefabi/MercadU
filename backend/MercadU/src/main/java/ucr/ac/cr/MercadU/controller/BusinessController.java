package ucr.ac.cr.MercadU.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.MercadU.model.dto.BusinessRequestDTO;
import ucr.ac.cr.MercadU.model.dto.BusinessResponseDTO;
import ucr.ac.cr.MercadU.service.BusinessService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/business")
@CrossOrigin(origins = "*")
public class BusinessController {

    @Autowired
    private BusinessService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveBusiness(@Validated @RequestBody BusinessRequestDTO dto, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            BusinessResponseDTO responseDto = this.service.saveBusiness(dto);
            if (responseDto == null) {
                throw new Exception();
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El emprendiminto '" + dto.getName() + "' ya se encuentra registrado.");
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        List<BusinessResponseDTO> resultado = this.service.findByName(name);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Lista Vacia");
        }
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBusiness(@PathVariable Integer id) {
        try {
            this.service.deleteBusiness(id);
            return ResponseEntity.ok("Se elimino el Emprendimiento correctamente.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Emprendimiento no encontrado.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editBusiness(@PathVariable Integer id, @RequestBody BusinessRequestDTO dto) {
        try {
            BusinessResponseDTO responseDto = this.service.editBusiness(id, dto);

            if (responseDto == null) {
                throw new Exception();
            }
            return ResponseEntity.ok(responseDto);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el emprendimiento.");
        }
    }




}
