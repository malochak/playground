package dev.mkon.parking

import dev.mkon.parking.exception.CarAlreadyParkedException
import dev.mkon.parking.exception.NoFreeSpotException
import spock.lang.Specification

class ParkingLotTest extends Specification {

    def "should initialize Parking Lot with given amount of spots"() {
        given:
            def spotsAmount = 5

        when:
            def parkingLot = new ParkingLot(spotsAmount)

        then:
            parkingLot.getAmountOfSpots() == spotsAmount
    }

    def "should add new Parking Spot"() {
        given:
            def parkingLot = new ParkingLot()
            def originalSize = parkingLot.getAmountOfSpots()

        when:
            parkingLot.addParkingSpot()

        then:
            parkingLot.getAmountOfSpots() == originalSize + 1
    }

    def "should remove free Parking Spot"() {
        given:
            def parkingLot = new ParkingLot(1)
            def originalSize = parkingLot.getAmountOfSpots()

        when:
            parkingLot.removeParkingSpot()

        then:
            parkingLot.getAmountOfSpots() == originalSize - 1
    }

    def "should throw when invalid initial amount of Parking Spots"() {
        given:
            def invalidSpotAmount = -1;

        when:
            new ParkingLot(invalidSpotAmount)

            Integer.parseInt()

        then:
            thrown(IllegalArgumentException)
    }

    def "should throw NoSuchElementException when no spots left to remove"() {
        given:
            def parkingLot = new ParkingLot()

        when:
            parkingLot.removeParkingSpot()

        then:
            thrown(NoFreeSpotException)
    }

    def "should park Vehicle when spot available"() {
        given:
            def parkingLot = new ParkingLot(1)
            def vehicle = new Vehicle("ZX090PX")

        when:
            parkingLot.parkVehicle(vehicle)

        then:
            parkingLot.getAmountOfFreeSpots() == 0
    }

    def "should throw NoFreeSpotsException when no free spot available"() {
        given:
            def parkingLot = new ParkingLot(1)
            def vehicle = new Vehicle("ZX090PX")
            def vehicle2 = new Vehicle("XZ909XP")

        when:
            parkingLot.parkVehicle(vehicle)
            parkingLot.parkVehicle(vehicle2)

        then:
            thrown(NoFreeSpotException)
    }

    def "should throw CarAlreadyParkedException when car parked"() {
        given:
            def parkingLot = new ParkingLot(1)
            def vehicle = new Vehicle("ZX090PX")

        when:
            parkingLot.parkVehicle(vehicle)
            parkingLot.parkVehicle(vehicle)

        then:
            thrown(CarAlreadyParkedException)
    }
}
