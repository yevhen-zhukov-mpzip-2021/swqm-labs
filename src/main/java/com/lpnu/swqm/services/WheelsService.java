package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Wheels;
import com.lpnu.swqm.exceptions.WheelsTypeNotGivenException;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class WheelsService {

    private static final Logger log = LogManager.getLogger(WheelsService.class.getName());

    public List<Wheels> findByType(List<Wheels> wheelsList,
                                   Wheels.Type type) {
        if (Objects.isNull(type)) {
            throw new WheelsTypeNotGivenException("Wheels type is null!");
        } else if (wheelsList.isEmpty()) {
            log.warn("Wheels-list is empty");
            return Collections.emptyList();
        }

        return wheelsList.stream()
                .filter(wheels -> wheels.getType().equals(type))
                .collect(Collectors.toList());
    }
}
