package dev.mkon.parking;

import java.util.Optional;

class ParkingSpot {
    private Optional<Vehicle> parkedVehicle = Optional.empty();

    void parkVehicle(Vehicle vehicle) {
        parkedVehicle = Optional.of(vehicle);
    }

    Optional<String>  getParkedCarLicensePlate() {
        return parkedVehicle.map(Vehicle::licensePlate);
    }

    void freeSpot() {
        parkedVehicle = Optional.empty();
    }

    boolean isFree() {
        return parkedVehicle.isEmpty();
    }
}