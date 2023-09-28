package com.ssafy.trip.dto.command;

public class ValidIdCommand {
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

    public ValidIdCommand toValidCommand(String memberId) {
        ValidIdCommand validIdCommand = new ValidIdCommand();
        validIdCommand.setMemberId(memberId);

        return validIdCommand;
    }
}
