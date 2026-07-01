package com.pictet.technologies.adventurelibrary.domain.exception;

import com.pictet.technologies.adventurelibrary.infrastructure.shared.utils.MessageUtils;
import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends BusinessException {
    public InternalServerErrorException() {
        super(MessageUtils.getMessage(MessageUtils.getMessage("error.internal")), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}

