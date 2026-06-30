package com.pictet.technologies.adventurelibrary.infrastructure.shared.utils;

import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
public class ObjectUtils {

    public <T> void updateIfChanged(
            Supplier<T> getter,
            Consumer<T> setter,
            T newValue
    ) {
        if (!Objects.equals(getter.get(), newValue)) {
            setter.accept(newValue);
        }
    }
}
