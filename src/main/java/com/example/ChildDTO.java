package com.example;

import jakarta.validation.constraints.NotNull;

import javax.xml.datatype.XMLGregorianCalendar;

public class ChildDTO {
    @NotNull(message = "date must not be blank")
    private XMLGregorianCalendar childDate;

    public XMLGregorianCalendar getChildDate() {
        return childDate;
    }

    public void setChildDate(XMLGregorianCalendar childDate) {
        this.childDate = childDate;
    }
}

