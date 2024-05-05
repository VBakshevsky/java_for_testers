package ru.stqa.addressbook.model;


public record UserData(String id, String firstname, String middlename, String lastname, String nickname, String title,
                       String company, String address, String home, String mobile, String work, String fax,
                       String email, String email2, String email3, String homepage, String bday, String bmonth,
                       String byear, String aday, String amonth, String ayear, String photo) {

    public UserData() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "0", "-", "", "0", "-", "", "");
    }

    public UserData withInitials(String firstname, String middlename, String lastname) {
        return new UserData(this.id, firstname, middlename, lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.bday, this.bmonth, this.aday, this.byear, this.amonth, this.ayear, this.photo);
    }

    public UserData withFirstName(String firstname) {
        return new UserData(this.id, firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.photo);
    }

    public UserData withMiddleName(String middlename) {
        return new UserData(this.id, this.firstname, middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.photo);
    }

    public UserData withLastName(String lastname) {
        return new UserData(this.id, this.firstname, this.middlename, lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.photo);
    }

    public UserData withId(String id) {
        return new UserData(id, this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, this.photo);
    }

    public UserData withDate(String bday, String bmonth, String byear, String aday, String amonth, String ayear) {
        return new UserData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage, bday, bmonth, byear, aday, amonth, ayear, this.photo);
    }

    public UserData withAllInformationWithoutFullName(String middlename, String nickname, String title, String company, String address, String home, String mobile, String work, String fax, String email, String email2, String email3, String homepage, String bday, String bmonth, String byear, String aday, String amonth, String ayear, String photo) {
        return new UserData(this.id, this.firstname, middlename, this.lastname, nickname, title, company, address, home, mobile, work, fax, email, email2, email3, homepage, bday, bmonth, byear, aday, amonth, ayear, photo);
    }

    public UserData withPhoto(String photo) {
        return new UserData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, photo);
    }

    public UserData withAllInformation(String id, String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String home, String mobile, String work, String fax, String email, String email2, String email3, String homepage, String bday, String bmonth, String byear, String aday, String amonth, String ayear) {
        return new UserData(id, firstname, middlename, lastname, nickname, title, company, address, home, mobile, work, fax, email, email2, email3, homepage, bday, bmonth, byear, aday, amonth, ayear, this.photo);
    }

    public UserData withMainInformation(String middlename, String lastname, String address, String firstname, String email2, String email3, String home, String mobile, String work, String photo, String email) {
        return new UserData(this.id, firstname, middlename, lastname, this.nickname, this.title, this.company, address, home, mobile, work, this.fax, email, email2, email3, this.homepage, this.bday, this.bmonth, this.byear, this.aday, this.amonth, this.ayear, photo);
    }
}