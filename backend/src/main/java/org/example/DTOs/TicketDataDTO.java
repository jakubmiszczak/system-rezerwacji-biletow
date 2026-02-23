package org.example.DTOs;

public class TicketDataDTO {
    private String row;
    private Integer number;

    public TicketDataDTO(String row, Integer number) {
        this.row = row;
        this.number = number;
    }

    public String getRow() {
        return row;
    }

    public Integer getNumber() {
        return number;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
