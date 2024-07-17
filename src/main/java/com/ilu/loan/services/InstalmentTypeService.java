package com.ilu.loan.services;

import java.util.List;

import com.ilu.loan.dto.request.InstalmentTypeRequest;
import com.ilu.loan.entities.InstalmentType;

public interface InstalmentTypeService {
    InstalmentType save(InstalmentTypeRequest instalmentType);

    InstalmentType getById(String id);

    List<InstalmentType> getAll();

    InstalmentType update(InstalmentTypeRequest request);

    boolean delete(String id);
}