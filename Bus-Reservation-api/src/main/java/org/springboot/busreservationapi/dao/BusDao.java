package org.springboot.busreservationapi.dao;

import org.springboot.busreservationapi.model.Bus;
import org.springboot.busreservationapi.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BusDao {

    @Autowired
    private BusRepository busRepository;

    //-------------------For adding New Bus---------------
    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    //-------------------Find Bus By Id---------------
    public Optional<Bus> findBusById(int busId) {
        return busRepository.findById(busId);
    }

    //-------------------Find All Buses---------------
    public List<Bus> findAll() {
        return busRepository.findAll();
    }

    //-------------------Update Bus----------------
    public Bus updateBus(Bus existingBus) {
        return busRepository.save(existingBus);
    }

    //-------------------Delete Bus by Id------------
    public void delete(int busId) {
        busRepository.deleteById(busId);
    }

    //------------------Find Bus By ArrivalCity and DepartureCity----------------
    public Optional<Bus> verifyBusByArrivalCityAndDepartureCity(String arrivalCity, String departureCity) {
        return busRepository.findByArrivalCityAndDepartureCity(arrivalCity, departureCity);
    }
}
