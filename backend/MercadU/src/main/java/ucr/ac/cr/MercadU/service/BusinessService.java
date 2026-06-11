package ucr.ac.cr.MercadU.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.MercadU.model.dto.BusinessRequestDTO;
import ucr.ac.cr.MercadU.model.dto.BusinessResponseDTO;
import ucr.ac.cr.MercadU.model.entity.Business;
import ucr.ac.cr.MercadU.repository.BusinessRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository repository;

    public BusinessResponseDTO saveBusiness (BusinessRequestDTO dto){
        Business business = new Business();
        business.setName(dto.getName());
        business.setDescription(dto.getDescription());
        business.setCategory(dto.getCategory());

        Business savedBusiness = this.repository.save(business);

        return this.toResponseDTO(savedBusiness);

    }

    //PUEDE UASRSE ESTE O EL DE CONVERT LIST
    public List<BusinessResponseDTO> findAll() {
        return this.repository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }


    public BusinessResponseDTO findById(Integer id) {
        Optional<Business> optional = this.repository.findById(id);

        //cambiarlo a try
        if (optional.isPresent()) {
            return this.toResponseDTO(optional.get());
        }


        return null;
    }

    public void deleteBusiness (Integer id){
        this.repository.deleteById(id);
    }


    public BusinessResponseDTO editBusiness(Integer id, BusinessRequestDTO dto) {
        try {
            Business business = this.repository.findById(id).get();

            business.setName(dto.getName());
            business.setDescription(dto.getDescription());
            business.setCategory(dto.getCategory());

            Business updatedBusiness = this.repository.save(business);
            return this.toResponseDTO(updatedBusiness);

        } catch (Exception e) {
            return null;
        }
    }

    public List<BusinessResponseDTO> findByName(String name) {
        return this.repository.findByName(name).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    //sirve pa convertir, pasa de business a BusinessResponseDTO
    private BusinessResponseDTO toResponseDTO(Business business) {
        return new BusinessResponseDTO(
                business.getId(),
                business.getName(),
                business.getDescription(),
                business.getCategory()
        );
    }

}
