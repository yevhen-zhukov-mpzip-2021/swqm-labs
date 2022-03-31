package com.lpnu.swqm.services;

import com.lpnu.swqm.domain.Abs;
import com.lpnu.swqm.exceptions.AbsTypeNotGivenException;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class AbsService {

    private static final Logger log = LogManager.getLogger(AbsService.class.getName());

    public List<Abs> findByType(List<Abs> absList,
                                Abs.Type type) {
        if (Objects.isNull(type)) {
            throw new AbsTypeNotGivenException("Abs type is null!");
        } else if (absList.isEmpty()) {
            log.warn("Abs-list is empty");
            return Collections.emptyList();
        }

        return absList.stream()
                .filter(abs -> abs.getType().equals(type))
                .collect(Collectors.toList());
    }
}
