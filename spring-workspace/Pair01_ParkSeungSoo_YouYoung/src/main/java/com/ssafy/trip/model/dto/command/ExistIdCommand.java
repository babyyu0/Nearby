package com.ssafy.trip.model.dto.command;

public class ExistIdCommand {
    private String memberId;

    public String getMemberId() {
        // 값이 입력되지 않을 경우 null return
        if(memberId == null || memberId.trim().equals(""))
            return null;

        return memberId.trim();
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public ExistIdCommand toValidCommand(String memberId) {
        ExistIdCommand validIdCommand = new ExistIdCommand();
        validIdCommand.setMemberId(memberId);

        return validIdCommand;
    }
}
