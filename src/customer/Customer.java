package customer;

import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDate;

public class Customer implements Serializable {
    private String idCustomer;
    private String nameCustomer;
    private LocalDate birthday;
    private String addressCustomer;
    private String phoneNumber;

    public Customer() {
    }

    public Customer(String idCustomer, String nameCustomer, LocalDate birthday, String addressCustomer, String phoneNumber) {
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.birthday = birthday;
        this.addressCustomer = addressCustomer;
        this.phoneNumber = phoneNumber;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
