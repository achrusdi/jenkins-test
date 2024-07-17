package com.ilu.loan.services.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ilu.loan.dto.request.InstalmentTypeRequest;
import com.ilu.loan.entities.InstalmentType;
import com.ilu.loan.repositories.InstalmentTypeRepository;
import com.ilu.loan.services.InstalmentTypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstalmentTypeServiceImpl implements InstalmentTypeService {

    private final InstalmentTypeRepository instalmentTypeRepository;

    @Override
    public InstalmentType update(InstalmentTypeRequest request) {
        if (instalmentTypeRepository.existsById(request.getId())) {
            InstalmentType instalmentType = new InstalmentType();
            instalmentType.setInstalmentType(request.getInstalmentType());
            instalmentType.setId(request.getId());
            return instalmentTypeRepository.save(instalmentType);
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(String id) {
        if (instalmentTypeRepository.existsById(id)) {
            instalmentTypeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<InstalmentType> getAll() {
        return instalmentTypeRepository.findAll();
    }

    @Override
    public InstalmentType getById(String id) {
        return instalmentTypeRepository.findById(id).orElse(null);
    }

    @Override
    public InstalmentType save(InstalmentTypeRequest request) {
        InstalmentType instalmentType = new InstalmentType();
        instalmentType.setInstalmentType(request.getInstalmentType());

        return instalmentTypeRepository.save(instalmentType);
    }

}