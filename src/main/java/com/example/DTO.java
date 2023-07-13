package com.example;

import jakarta.validation.constraints.*;

import javax.xml.datatype.XMLGregorianCalendar;

public class DTO {
    @NotBlank(message = "name must not be blank")
    private String name;
    @NotNull(message = "date must not be blank")
    private XMLGregorianCalendar date;

    public ChildDTO getChildDTO() {
        return childDTO;
    }

    public void setChildDTO(ChildDTO childDTO) {
        this.childDTO = childDTO;
    }

    private ChildDTO childDTO;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public XMLGregorianCalendar getDate() {
        return date;
    }

    public void setDate(XMLGregorianCalendar date) {
        this.date = date;
    }
}

