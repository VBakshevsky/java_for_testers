package model;


public record UserData(String firstname, String middlename, String lastname, String nickname, String title,
                       String company, String address, String home, String mobile, String work, String fax,
                       String email, String email2, String email3, String homepage, String bday, String bmonth,
                       String byear, String amonth, String aday, String ayear) {

    public UserData() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "-", "", "-", "", "");
    }

    public UserData withInitials(String firstname, String middlename, String lastname) {
        return new UserData(firstname, middlename, lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.bday, this.bmonth, this.byear, this.amonth, this.aday, this.ayear);
    }

    public UserData withMainInformation(String firstname, String middlename, String lastname,String address, String email, String email2, String email3, String mobile) {
        return new UserData(firstname, middlename, lastname, this.nickname, this.title, this.company, address, this.home, mobile, this.work, this.fax, email, email2, email3, this.homepage, this.bday, this.bmonth, this.byear, this.amonth, this.aday, this.ayear);
    }
}