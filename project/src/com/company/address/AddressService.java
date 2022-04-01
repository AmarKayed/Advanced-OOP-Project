package com.company.address;

import com.company.Service;

public class AddressService implements AddressServiceInterface{

    private static AddressService instance;

    private AddressService(){}

    public static AddressService getInstance(){
        if (instance == null)
            instance = new AddressService();
        return instance;
    }

    @Override
    public Address readAddress() {
        String country, city;

        System.out.println("Country: ");
        country = Service.getInstance().getSc().nextLine();

        System.out.println("City: ");
        city = Service.getInstance().getSc().nextLine();

        Address ob = new Address(country, city);
        return ob;
    }

    @Override
    public void updateAdress(Address toUpdate){
        System.out.println("Country: " + toUpdate.getCountry());
        System.out.println("City Update Value: ");
        String country;
        country = Service.getInstance().getSc().nextLine();

        if (!country.equals("_keep"))
            toUpdate.setCountry(country);

        System.out.println("City: " + toUpdate.getCity());
        System.out.println("City Update Value: ");
        String city;
        city = Service.getInstance().getSc().nextLine();

        if(!city.equals("_keep"))
            toUpdate.setCity(city);

    }

}
