package com.practice.TrackZilla.exception;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(String exception) {
        super(exception);
    }
}
