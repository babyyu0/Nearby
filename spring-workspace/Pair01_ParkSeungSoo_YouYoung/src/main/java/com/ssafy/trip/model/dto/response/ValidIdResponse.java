package com.ssafy.trip.model.dto.response;

import com.ssafy.trip.util.exception.common.MessageInvalidException;

public class ValidIdResponse {
    public ValidIdResponse(boolean isValid, String message) throws MessageInvalidException {
        setValid(isValid);
        setMessage(message);
    }
    private boolean isValid;
    private String message;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void setMessage(String message) throws MessageInvalidException {
        if(message == null || message.trim().equals("")) {
            throw new MessageInvalidException();
        }
        this.message = message.trim();
    }
}
