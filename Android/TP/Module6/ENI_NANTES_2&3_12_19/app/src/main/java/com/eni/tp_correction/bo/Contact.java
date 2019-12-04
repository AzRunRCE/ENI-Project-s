package com.eni.tp_correction.bo;

/**
 * Created by quentin for TP_Correction on 2019-12-03.
 */
public class Contact {
    private String number;
    private String displayName;

    public Contact(String number, String displayName) {
        this.number = number;
        this.displayName = displayName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "number='" + number + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
