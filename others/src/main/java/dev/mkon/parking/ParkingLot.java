package dev.mkon.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import dev.mkon.parking.exception.CarAlreadyParkedException;
import dev.mkon.parking.exception.NoFreeSpotException;

class ParkingLot {

    private final List<ParkingSpot> parkingSpots = new ArrayList<>();

    ParkingLot() {
    }

    ParkingLot(int spotsAmount) {
        if (spotsAmount < 0) {
            throw new IllegalArgumentException(
                "Invalid amount of initial Parking Spots: %s".formatted(spotsAmount));
        }

        List<ParkingSpot> newSpots =
            IntStream.range(0, spotsAmount).mapToObj(num -> new ParkingSpot()).toList();
        parkingSpots.addAll(newSpots);
    }

    void addParkingSpot() {
        parkingSpots.add(new ParkingSpot());
    }

    void removeParkingSpot() {
        ParkingSpot freeParkingSpot =
            parkingSpots.stream().filter(ParkingSpot::isFree).findFirst().orElseThrow(() ->
                new NoFreeSpotException(
                    "All spots are occupied - cannot remove any at the moment."));

        parkingSpots.remove(freeParkingSpot);
    }

    void parkVehicle(Vehicle vehicle) {
        boolean
            vehicleAlreadyParked =
            parkingSpots.stream().map(ParkingSpot::getParkedCarLicensePlate).anyMatch(
                licensePlate -> licensePlate.isPresent() && licensePlate.get()
                    .equals(vehicle.licensePlate()));

        if (vehicleAlreadyParked) {
            throw new CarAlreadyParkedException(
                "Vehicle with license plate '%s' already entered park lot".formatted(
                    vehicle.licensePlate()));
        }

        ParkingSpot freeSpot = parkingSpots.stream().filter(ParkingSpot::isFree).findFirst()
            .orElseThrow(() -> new NoFreeSpotException("There's no free spot right now"));

        freeSpot.parkVehicle(vehicle);
    }

    int getAmountOfSpots() {
        return parkingSpots.size();
    }

    long getAmountOfFreeSpots() {
        return parkingSpots.stream().filter(ParkingSpot::isFree).count();
    }
}