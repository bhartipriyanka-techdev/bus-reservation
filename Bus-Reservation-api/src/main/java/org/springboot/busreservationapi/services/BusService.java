package org.springboot.busreservationapi.services;

import org.springboot.busreservationapi.Exception.BusNotFoundException;
import org.springboot.busreservationapi.dao.AdminDao;
import org.springboot.busreservationapi.dao.BusDao;
import org.springboot.busreservationapi.dto.BusRequest;
import org.springboot.busreservationapi.dto.BusResponse;
import org.springboot.busreservationapi.dto.ResponseStructure;
import org.springboot.busreservationapi.model.Admin;
import org.springboot.busreservationapi.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing Bus operations.
 */
@Service
public class BusService {

    @Autowired
    private BusDao busDao;

    @Autowired
    private AdminDao adminDao;

    /**
     * Maps a BusRequest to a Bus entity.
     *
     * @param busRequest the bus request containing bus details
     * @return the Bus entity
     */
    private Bus mapToBus(BusRequest busRequest) {
        return Bus.builder()
                .busName(busRequest.getBusName())
                .busType(busRequest.getBusType())
                .busNumber(busRequest.getBusNumber())
                .arrivalCity(busRequest.getArrivalCity())
                .departureCity(busRequest.getDepartureCity())
                .arrivalTime(busRequest.getArrivalTime())
                .departureTime(busRequest.getDepartureTime())
                .capacity(busRequest.getCapacity())
                .availability(busRequest.isAvailability())
                .price(busRequest.getPrice())
                .build();
    }

    /**
     * Maps a Bus to a BusResponse.
     *
     * @param bus the bus entity
     * @return the BusResponse
     */
    private BusResponse mapToBusResponse(Bus bus) {
        return BusResponse.builder()
                .busName(bus.getBusName())
                .busType(bus.getBusType())
                .busNumber(bus.getBusNumber())
                .arrivalCity(bus.getArrivalCity())
                .departureCity(bus.getDepartureCity())
                .arrivalTime(bus.getArrivalTime())
                .departureTime(bus.getDepartureTime())
                .capacity(bus.getCapacity())
                .availability(bus.isAvailability())
                .price(bus.getPrice())
                .build();
    }

    /**
     * Adds a new bus.
     *
     * @param bus the bus entity to add
     * @param adminId the ID of the admin adding the bus
     * @return a response entity containing the added bus and a response structure
     */
    public ResponseEntity<ResponseStructure<BusResponse>> addBus(Bus bus, int adminId) {
        ResponseStructure<BusResponse> responseStructure = new ResponseStructure<>();

        // Check if adminId is provided
        if (adminId == 0) {
            responseStructure.setMessage("Admin ID is required.");
            responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
            responseStructure.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseStructure);
        }

        // Check if the adminId exists
        Admin admin = adminDao.findAdminById(adminId).orElse(null);
        if (admin == null) {
            responseStructure.setMessage("Admin ID does not exist.");
            responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
            responseStructure.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseStructure);
        }

        // Set the adminId to the bus
        bus.setAdmin(admin);

        // Save the bus
        Bus savedBus = busDao.addBus(bus);

        // Prepare the response structure
        responseStructure.setMessage("Bus Added Successfully!");
        responseStructure.setStatusCode(HttpStatus.CREATED.value());
        responseStructure.setData(mapToBusResponse(savedBus));

        return ResponseEntity.status(HttpStatus.CREATED).body(responseStructure);
    }

    /**
     * Finds a bus by its ID.
     *
     * @param busId the ID of the bus to find
     * @return a response entity containing the found bus and a response structure
     */
    public ResponseEntity<ResponseStructure<BusResponse>> findBusById(int busId) {
        ResponseStructure<BusResponse> responseStructure = new ResponseStructure<>();
        Optional<Bus> rcvBus = busDao.findBusById(busId);
        if (rcvBus.isPresent()) {
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setData(mapToBusResponse(rcvBus.get()));
            return ResponseEntity.status(HttpStatus.OK).body(responseStructure);
        }
        throw new BusNotFoundException("Please Check, You entered Invalid Bus Id");
    }

    /**
     * Finds all buses.
     *
     * @return a response entity containing the list of all buses and a response structure
     */
    public ResponseEntity<ResponseStructure<List<BusResponse>>> findAllBuses() {
        ResponseStructure<List<BusResponse>> structure = new ResponseStructure<>();
        List<Bus> buses = busDao.findAll();
        List<BusResponse> busResponses = buses.stream().map(this::mapToBusResponse).collect(Collectors.toList());
        structure.setData(busResponses);
        structure.setMessage("List of All Buses");
        structure.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(structure);
    }

    /**
     * Updates an existing bus.
     *
     * @param busId the ID of the bus to update
     * @param updatedBus the updated bus details
     * @return a response entity containing the updated bus and a response structure
     */
    public ResponseEntity<ResponseStructure<BusResponse>> updateBus(int busId, Bus updatedBus) {
        ResponseStructure<BusResponse> responseStructure = new ResponseStructure<>();
        Optional<Bus> existingBusOptional = busDao.findBusById(busId);

        if (existingBusOptional.isPresent()) {
            Bus existingBus = existingBusOptional.get();
            // Update the fields of the existing bus with the new values
            existingBus.setBusName(updatedBus.getBusName());
            existingBus.setBusNumber(updatedBus.getBusNumber());
            existingBus.setBusType(updatedBus.getBusType());
            existingBus.setAvailability(updatedBus.isAvailability());
            existingBus.setArrivalCity(updatedBus.getArrivalCity());
            existingBus.setDepartureCity(updatedBus.getDepartureCity());
            existingBus.setArrivalTime(updatedBus.getArrivalTime());
            existingBus.setDepartureTime(updatedBus.getDepartureTime());
            existingBus.setCapacity(updatedBus.getCapacity());
            existingBus.setPrice(updatedBus.getPrice());

            // Save the updated Bus back to the database
            Bus savedBus = busDao.updateBus(existingBus);

            responseStructure.setMessage("Bus Updated Successfully");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setData(mapToBusResponse(savedBus));
            return ResponseEntity.status(HttpStatus.OK).body(responseStructure);
        }
        throw new BusNotFoundException("Please Check, You entered Invalid Bus Id");
    }

    /**
     * Deletes a bus by its ID.
     *
     * @param busId the ID of the bus to delete
     * @return a response entity containing a confirmation message and a response structure
     */
    public ResponseEntity<ResponseStructure<String>> deleteBus(int busId) {
        ResponseStructure<String> structure = new ResponseStructure<>();
        Optional<Bus> rcvBus = busDao.findBusById(busId);
        if (rcvBus.isPresent()) {
            structure.setData("Bus Found");
            structure.setMessage("Bus deleted Successfully");
            structure.setStatusCode(HttpStatus.OK.value());
            busDao.delete(busId);
            return ResponseEntity.status(HttpStatus.OK).body(structure);
        }
        throw new BusNotFoundException("Please Check, You entered Invalid Bus Id");
    }

    /**
     * Verifies a bus by its arrival city and departure city.
     *
     * @param arrivalCity the arrival city of the bus
     * @param departureCity the departure city of the bus
     * @return a response entity containing the verified bus and a response structure
     */
    public ResponseEntity<ResponseStructure<BusResponse>> verifyBusByArrivalCityAndDepartureCity(String arrivalCity, String departureCity) {
        ResponseStructure<BusResponse> responseStructure = new ResponseStructure<>();
        Optional<Bus> rcvBus = busDao.verifyBusByArrivalCityAndDepartureCity(arrivalCity, departureCity);
        if (rcvBus.isPresent()) {
            responseStructure.setMessage("Bus Found");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setData(mapToBusResponse(rcvBus.get()));
            return ResponseEntity.status(HttpStatus.OK).body(responseStructure);
        }
        throw new BusNotFoundException("Please Check, You entered Invalid ArrivalCity And DepartureCity");
    }
}
