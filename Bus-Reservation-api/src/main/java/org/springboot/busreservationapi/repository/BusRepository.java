package org.springboot.busreservationapi.repository;
import org.springboot.busreservationapi.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Integer> {
    @Query("SELECT b FROM Bus b WHERE b.arrivalCity = :arrivalCity AND b.departureCity = :departureCity")

    Optional<Bus> findByArrivalCityAndDepartureCity(String arrivalCity, String departureCity);

  /*  Optional<Bus> findByBusNumber(String busNumber);

    Optional<Bus> findByBusName(String busName);*/
}
