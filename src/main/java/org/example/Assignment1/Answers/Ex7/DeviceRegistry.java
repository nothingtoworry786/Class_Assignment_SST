package org.example.Assignment1.Answers.Ex7;

import java.util.ArrayList;
import java.util.List;

public class DeviceRegistry {
    private final List<Object> devices = new ArrayList<>();

    public void add(Object d) { devices.add(d); }

    public <T> T getFirstOfType(Class<T> capability) {
        for (Object device : devices) {
            if (capability.isInstance(device)) {
                return capability.cast(device);
            }
        }
        throw new IllegalStateException("Missing capability: " + capability.getSimpleName());
    }
    public <T> List<T> getAllByCapability(Class<T> capability) {
        List<T> result = new ArrayList<>();
        for (Object device : devices) {
            if (capability.isInstance(device)) {
                result.add(capability.cast(device));
            }
        }
        return result;
    }
}
