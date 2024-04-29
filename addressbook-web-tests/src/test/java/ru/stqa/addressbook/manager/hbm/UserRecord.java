package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class UserRecord {
    @Id
    public int id;
    public String firstname;
    public String middlename;
    public String lastname;
    public String nickname;
    public String title;
    public String company;
    public String address;
    public String home;
    public String mobile;
    public String work;
    public String fax;
    public String email;
    public String email2;
    public String email3;
    public String homepage;
    public String bday;
    public String bmonth;
    public String byear;
    public String aday;
    public String amonth;
    public String ayear;
    public String photo;

}