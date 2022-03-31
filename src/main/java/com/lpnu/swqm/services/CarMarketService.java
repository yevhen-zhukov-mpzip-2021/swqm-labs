package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.SpareParts;
import com.lpnu.swqm.exceptions.SparePartsTypeNotGivenException;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class CarMarketService {

    private static final Logger log = LogManager.getLogger(CarMarketService.class.getName());

    public <T extends SpareParts> List<T> findByType(List<SpareParts> spareParts,
                                                     Class<T> sparePartsType) {
        if (Objects.isNull(sparePartsType)) {
            throw new SparePartsTypeNotGivenException("Spare parts type is null!");
        } else if (spareParts.isEmpty()) {
            log.warn("Spare parts-list is empty");
            return Collections.emptyList();
        }

        return spareParts.stream()
                .filter(sparePart -> sparePart.getClass().equals(sparePartsType))
                .map(sparePartsType::cast)
                .collect(Collectors.toList());
    }
}
