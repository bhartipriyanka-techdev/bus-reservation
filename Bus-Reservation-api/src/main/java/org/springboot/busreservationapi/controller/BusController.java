package org.springboot.busreservationapi.controller;

import jakarta.validation.Valid;
import org.springboot.busreservationapi.dto.BusResponse;
import org.springboot.busreservationapi.dto.ResponseStructure;
import org.springboot.busreservationapi.model.Bus;
import org.springboot.busreservationapi.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing Bus operations.
 */
@RestController
@RequestMapping(value = "/api/bus")
public class BusController {

    @Autowired
    private BusService busService;

    /**
     * Adds a new bus.
     *
     * @param bus the bus entity to add
     * @param adminId the ID of the admin adding the bus
     * @return a response entity containing the added bus and a response structure
     */
    @PostMapping("/{adminId}")
    public ResponseEntity<ResponseStructure<BusResponse>> addBus(@Valid @RequestBody Bus bus, @PathVariable int adminId) {
        return busService.addBus(bus, adminId);
    }

    /**
     * Finds a bus by its ID.
     *
     * @param busId the ID of the bus to find
     * @return a response entity containing the found bus and a response structure
     */
    @GetMapping("/{busId}")
    public ResponseEntity<ResponseStructure<BusResponse>> findBusById(@PathVariable int busId) {
        return busService.findBusById(busId);
    }

    /**
     * Finds all buses.
     *
     * @return a response entity containing the list of all buses and a response structure
     */
    @GetMapping
    public ResponseEntity<ResponseStructure<List<BusResponse>>> findAllBuses() {
        return busService.findAllBuses();
    }

    /**
     * Updates an existing bus.
     *
     * @param busId the ID of the bus to update
     * @param updatedBus the updated bus details
     * @return a response entity containing the updated bus and a response structure
     */
    @PutMapping("/{busId}")
    public ResponseEntity<ResponseStructure<BusResponse>> updateBus(@PathVariable int busId, @RequestBody Bus updatedBus) {
        return busService.updateBus(busId, updatedBus);
    }

    /**
     * Deletes a bus by its ID.
     *
     * @param busId the ID of the bus to delete
     * @return a response entity containing a confirmation message and a response structure
     */
    @DeleteMapping("/{busId}")
    public ResponseEntity<ResponseStructure<String>> deleteBus(@PathVariable int busId) {
        return busService.deleteBus(busId);
    }

    /**
     * Verifies a bus by its arrival city and departure city.
     *
     * @param arrivalCity the arrival city of the bus
     * @param departureCity the departure city of the bus
     * @return a response entity containing the verified bus and a response structure
     */
    @PostMapping("/verify/arrivalCity/departureCity")
    public ResponseEntity<ResponseStructure<BusResponse>> verifyBusByArrivalCityAndDepartureCity(
            @RequestParam String arrivalCity, @RequestParam String departureCity) {
        return busService.verifyBusByArrivalCityAndDepartureCity(arrivalCity, departureCity);
    }
}

