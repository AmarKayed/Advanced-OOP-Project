package com.company.address;

import java.util.Objects;

public class Address {

    private String country;
    private String city;

    public Address() {
    }

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
//        return "country='" + country + '\'' +
//                ", city='" + city + '\'';


//
//        return "Address{" +
//                "country='" + country + '\'' +
//                ", city='" + city + '\'' +
//                '}';
//
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return country.equals(address.country) && city.equals(address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city);
    }


}
