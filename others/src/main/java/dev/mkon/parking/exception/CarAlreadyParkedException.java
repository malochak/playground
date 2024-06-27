package dev.mkon.parking.exception;

public class CarAlreadyParkedException extends RuntimeException {

    public CarAlreadyParkedException(String message) {
        super(message);
    }
}
