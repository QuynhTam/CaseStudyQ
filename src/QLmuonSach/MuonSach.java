package QLmuonSach;

import java.io.Serializable;
import java.time.LocalDate;

public class MuonSach implements Serializable {
    private String customerCode;
    private String bookCode;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private int money;
    private int quantity;

    public MuonSach() {
    }

    public MuonSach(String customerCode, String bookCode, LocalDate rentalDate, LocalDate returnDate, int money, int quantity) {
        this.customerCode = customerCode;
        this.bookCode = bookCode;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.money = money;
        this.quantity = quantity;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
