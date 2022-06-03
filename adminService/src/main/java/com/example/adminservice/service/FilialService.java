package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.repository.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilialService {

    private final FilialRepository filialRepository;

    public ApiResponse save(Filial filial){
        Filial save = filialRepository.save(filial);
        return new ApiResponse("save",true);
    }

    public ApiResponse updete(Long id, Filial filial){
        Optional<Filial> byId = filialRepository.findById(id);
        if (byId.isPresent()) {
            Filial filial1 = byId.get();
            filial1.setNameUz(filial.getNameUz());
            filial1.setNameRu(filial.getNameRu());
            filial1.setAddress(filial.getAddress());
            filial1.setIntended(filial.getIntended());
            filial1.setStart(filial.getStart());
            filial1.setFinish(filial.getFinish());
            filial1.setLongitude(filial.getLongitude());
            filial1.setLatitude(filial.getLatitude());
            filialRepository.save(filial1);
            return new ApiResponse("save",true);
        }else {
            return new ApiResponse("Mavjud emas",false);
        }

    }

}
