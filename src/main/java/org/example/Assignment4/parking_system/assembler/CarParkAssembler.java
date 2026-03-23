package org.example.Assignment4.parking_system.assembler;

import org.example.Assignment4.parking_system.model.CarParkConfig;
import org.example.Assignment4.parking_system.service.CarPark;
import org.example.Assignment4.parking_system.strategy.DefaultSlotDecisionStrategy;

public class CarParkAssembler {
    public CarPark assemble(CarParkConfig config) {
        return new CarPark(config, new DefaultSlotDecisionStrategy());
    }
}

