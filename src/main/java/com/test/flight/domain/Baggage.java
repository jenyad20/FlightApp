package com.test.flight.domain;

/**
 * Created by jenya on 11/30/18.
 */
public class Baggage {

    private String id;
    private long destinationId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Baggage baggage = (Baggage) o;

        if (getDestinationId() != baggage.getDestinationId()) return false;
        return getId().equals(baggage.getId());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (int) (getDestinationId() ^ (getDestinationId() >>> 32));
        return result;
    }
}
