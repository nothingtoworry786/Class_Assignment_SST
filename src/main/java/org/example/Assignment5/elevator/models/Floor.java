package org.example.Assignment5.elevator.models;

import org.example.Assignment5.elevator.requests.ExternalRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Floor {
    private final int floorNumber;
    private final List<ExternalRequest> pendingExternalRequests = new ArrayList<>();

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void addRequest(ExternalRequest request) {
        pendingExternalRequests.add(request);
    }

    public List<ExternalRequest> getPendingExternalRequests() {
        return Collections.unmodifiableList(pendingExternalRequests);
    }
}
