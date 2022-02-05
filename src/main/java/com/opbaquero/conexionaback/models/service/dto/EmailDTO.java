package com.opbaquero.conexionaback.models.service.dto;

public class EmailDTO {

    private String from;
    private String to;
    private String subject;
    private String userName;
    private String newPassword;

    public EmailDTO(){

    }

    public EmailDTO(String from, String to, String subject, String userName, String newPassword) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.userName = userName;
        this.newPassword = newPassword;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
