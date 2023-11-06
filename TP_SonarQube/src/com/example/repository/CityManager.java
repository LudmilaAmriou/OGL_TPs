package com.example.repository;

import com.example.model.City;
import com.example.service.DatabaseService;

import java.util.List;

public class CityManager implements ICityManager{

    private DatabaseService databaseService;

    public DatabaseService getDatabaseService() {
        return databaseService;
    }

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public void addCity(City city) {
        databaseService.addCity(city);

    }

    @Override
    public City getCity(int idCity) {
        return databaseService.getCity(idCity);
    }

    @Override
    public List<City> getCities() {
        return databaseService.getCities();
    }
}
