# Assignment4 — Parking System (Class Diagram)

```mermaid
classDiagram

classDef core fill:#F3E5F5,stroke:#6A1B9A,color:#4A148C;
classDef enum fill:#E8F5E9,stroke:#2E7D32,color:#1B5E20;

class ParkingSimulation core;
class CarPark core;
class CarParkAssembler core;
class Vehicle core;
class ParkReceipt core;
class CarParkConfig core;
class SpotSpec core;

class SpotCategory enum;
class VehicleType enum;

class ParkingSimulation {
  +main(args: String[]): void
}

class CarParkAssembler {
  +assemble(config: CarParkConfig): CarPark
}

class CarPark {
  +status(): Map~SpotCategory, Long~
  +park(vehicle: Vehicle, entryTime: LocalDateTime, requestedSlot: SpotCategory, entranceId: int): ParkReceipt
  +exit(receipt: ParkReceipt, exitTime: LocalDateTime): double
  +minSlotFor(vehicleType: VehicleType): SpotCategory
}

class Vehicle {
  +Vehicle(plate: String, type: VehicleType)
  +getType(): VehicleType
}

class ParkReceipt {
  +getAllocatedSlotType(): SpotCategory
  +getAllocatedSlotId(): String
  +getReservedSpot(): SpotSpec
}

class CarParkConfig {
  +setTotalFloors(n: int): CarParkConfig
  +setEntrancesPerFloor(arr: int[]): CarParkConfig
  +addFloorSpecs(specs: List~SpotSpec~): void
}

class SpotSpec {
  +SpotSpec(cat: SpotCategory, rate: double, distances: int[])
  +getRatePerHour(): double
  +getCategory(): SpotCategory
}

class SpotCategory {
  <<enumeration>>
  SMALL
  MEDIUM
  LARGE
}

class VehicleType {
  <<enumeration>>
  BIKE
  CAR
  TRUCK
}

%% Relationships
ParkingSimulation --> CarParkAssembler
ParkingSimulation --> CarPark
ParkingSimulation --> Vehicle
ParkingSimulation --> CarParkConfig
ParkingSimulation --> SpotSpec

CarPark --> Vehicle
CarPark --> ParkReceipt
CarPark --> SpotCategory
CarPark --> VehicleType : uses for slot decision

Vehicle --> VehicleType

ParkReceipt --> SpotCategory
ParkReceipt --> SpotSpec
```
