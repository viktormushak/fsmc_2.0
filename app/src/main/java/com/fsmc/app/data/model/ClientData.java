package com.fsmc.app.data.model;

public class ClientData {
    private int hashId;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    private String email;
    private boolean hasEmail;

    public void setHashId(int hashId) {
        this.hashId = hashId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHasEmail(boolean hasEmail) {
        this.hasEmail = hasEmail;
    }

    public int getHashId() {
        return hashId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isHasEmail() {
        return hasEmail;
    }
}
