package main;
public class DBRecordModel {
    private final String nickname, city, zipCode, country;

    public DBRecordModel(String nickname, String city, String zipCode, String country) {
        this.nickname = nickname;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }
}
