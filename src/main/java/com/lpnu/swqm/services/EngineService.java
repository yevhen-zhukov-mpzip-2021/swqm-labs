package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Engine;
import com.lpnu.swqm.exceptions.EngineNotGivenException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Getter
public class EngineService {

    private static final Logger log = LogManager.getLogger(EngineService.class.getName());

    public Engine findBySerialNumber(List<Engine> absList,
                                     String serialNumber) {
        if (StringUtils.isBlank(serialNumber)) {
            throw new EngineNotGivenException("Engine type is null!");
        } else if (absList.isEmpty()) {
            log.warn("Engine-list is empty");
            return null;
        }

        return absList.stream()
                .filter(engine -> engine.getSerialNumber().equals(serialNumber))
                .findFirst()
                .orElseGet(() -> {
                    log.warn("No any Engine found by serial number {}", serialNumber);
                    return null;
                });
    }
}
