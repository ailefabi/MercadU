package ucr.ac.cr.MercadU.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.MercadU.model.User;
import ucr.ac.cr.MercadU.model.dto.LoginUserDTO;
import ucr.ac.cr.MercadU.model.dto.UserDTO;
import ucr.ac.cr.MercadU.service.UserService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> findall(){
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        UserDTO dto = userService.findByIDUser(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@Validated @RequestBody User user, BindingResult result){
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()){
                errors.put(error.getField(),error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        UserDTO dto = this.userService.saveUser(user);
        if (dto == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario con el ID "+user.getId()+" ya se encuentra registrado.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDTO dtologin){
        User user = this.userService.login(dtologin.getEmail(),dtologin.getPassword());
        if (user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas.");
        }
        return ResponseEntity.ok("Bienvenido "+user.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}

