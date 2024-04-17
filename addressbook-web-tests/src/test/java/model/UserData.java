package model;


public record UserData(String id, String firstname, String middlename, String lastname, String nickname, String title,
                       String company, String address, String home, String mobile, String work, String fax,
                       String email, String email2, String email3, String homepage, String bday, String bmonth,
                       String byear, String aday, String amonth, String ayear) {

    public UserData() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "-", "-", "", "-", "-", "");
    }

    public UserData withInitials(String firstname, String middlename, String lastname) {
        return new UserData(this.id, firstname, middlename, lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.bday, this.bmonth, this.byear, this.amonth, this.aday, this.ayear);
    }
    public UserData withFirstAndLastNames(String firstname, String lastname) {
        return new UserData(this.id, firstname, this.middlename, lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.bday, this.bmonth, this.byear, this.amonth, this.aday, this.ayear);
    }
    public UserData withId(String id) {
        return new UserData(id, this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.bday, this.bmonth, this.byear, this.amonth, this.aday, this.ayear);
    }

    public UserData withDate(String bday, String bmonth, String byear, String aday, String amonth, String ayear) {
        return new UserData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage, bday, bmonth, byear, aday, amonth, ayear);
    }

    public UserData withMainInformation(String firstname, String middlename, String lastname,String address, String email, String email2, String email3, String mobile) {
        return new UserData(this.id, firstname, middlename, lastname, this.nickname, this.title, this.company, address, this.home, mobile, this.work, this.fax, email, email2, email3, this.homepage, this.bday, this.bmonth, this.byear, this.amonth, this.aday, this.ayear);
    }
}