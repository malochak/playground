package dev.mkon.parking.exception;

public class NoFreeSpotException extends RuntimeException {

    public NoFreeSpotException(String message) {
        super(message);
    }
}
