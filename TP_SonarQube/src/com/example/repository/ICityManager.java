package com.example.repository;

import com.example.model.City;

import java.util.List;

public interface ICityManager {

    public void addCity(City city);

    public  City getCity(int idCity);

    public  List<City> getCities();

}
