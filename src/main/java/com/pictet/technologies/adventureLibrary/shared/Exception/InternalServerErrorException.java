package com.pictet.technologies.adventureLibrary.shared.Exception;

import com.pictet.technologies.adventureLibrary.shared.utils.MessageUtils;
import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends BusinessException {
    public InternalServerErrorException() {
        super(MessageUtils.getMessage("internal.server.error"), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}

