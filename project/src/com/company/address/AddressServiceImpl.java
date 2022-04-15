package com.company.address;

import com.company.Service;

public class AddressServiceImpl implements AddressService {

    private static AddressServiceImpl instance;

    private AddressServiceImpl(){}

    public static AddressServiceImpl getInstance(){
        if (instance == null)
            instance = new AddressServiceImpl();
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
        System.out.println("Country Update Value: ");
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
