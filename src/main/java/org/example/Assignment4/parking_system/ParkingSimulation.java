package org.example.Assignment4.parking_system;

import org.example.Assignment4.parking_system.assembler.CarParkAssembler;
import org.example.Assignment4.parking_system.controller.ParkingController;
import org.example.Assignment4.parking_system.model.CarParkConfig;
import org.example.Assignment4.parking_system.model.ParkReceipt;
import org.example.Assignment4.parking_system.model.Vehicle;
import org.example.Assignment4.parking_system.model.VehicleType;
import org.example.Assignment4.parking_system.service.CarPark;
import org.example.Assignment4.parking_system.spots.SpotCategory;
import org.example.Assignment4.parking_system.spots.SpotSpec;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParkingSimulation {
    private final int TOTAL_FLOORS = 3;
    private final int ENTRANCES_PER_FLOOR = 2;
    private final int SPOTS_PER_FLOOR = 6;
    private final int TOTAL_VEHICLES = 12;
    private final int THREAD_POOL_SIZE = 4;

    public static void main(String[] args) throws Exception {
        ParkingSimulation sim = new ParkingSimulation();
        CarPark carPark = sim.buildCarPark();
        ParkingController controller = new ParkingController(carPark);

        ExecutorService executor = Executors.newFixedThreadPool(sim.THREAD_POOL_SIZE);
        List<Future<Double>> futures = new ArrayList<>();

        for (int i = 0; i < sim.TOTAL_VEHICLES; i++) {
            final int idx = i;
            futures.add(executor.submit(() -> {
                VehicleType vt = switch (idx % 3) {
                    case 0 -> VehicleType.BIKE;
                    case 1 -> VehicleType.CAR;
                    default -> VehicleType.TRUCK;
                };
                Vehicle vehicle = new Vehicle("PLATE-" + idx, vt);

                // request "around" the minimum size; CarPark will upgrade if needed.
                SpotCategory requested = SpotCategory.values()[Math.min(idx % 3, 2)];
                int entranceId = idx % (sim.TOTAL_FLOORS * sim.ENTRANCES_PER_FLOOR);

                LocalDateTime entry = LocalDateTime.now().plusSeconds(idx);
                ParkReceipt receipt = controller.park(vehicle, entry, requested, entranceId);

                // park for some time (30..180 minutes) then exit
                LocalDateTime exit = entry.plusMinutes(30L * (1 + (idx % 6)));
                return controller.exit(receipt, exit);
            }));
        }

        for (Future<Double> f : futures) {
            f.get(); // ensure all tasks complete
        }

        executor.shutdown();

        sim.printStatus(controller.status());
    }

    private void printStatus(Map<SpotCategory, Long> statusMap) {
        System.out.println("=== Car park available spots by category ===");
        for (SpotCategory c : SpotCategory.values()) {
            long n = statusMap.getOrDefault(c, 0L);
            System.out.println(c + " => " + n);
        }
    }

    private CarPark buildCarPark() {
        CarParkConfig config = new CarParkConfig()
                .setTotalFloors(TOTAL_FLOORS)
                .setEntrancesPerFloor(buildEntrancesArray());

        int[] distances = new int[SPOTS_PER_FLOOR];
        for (int i = 0; i < SPOTS_PER_FLOOR; i++) {
            distances[i] = i; // relative distance value (smaller = "closer")
        }

        // Rates can vary per category. Distances length controls number of spots per floor.
        List<SpotSpec> specs = List.of(
                new SpotSpec(SpotCategory.SMALL, 5.0, distances),
                new SpotSpec(SpotCategory.MEDIUM, 8.0, distances),
                new SpotSpec(SpotCategory.LARGE, 12.0, distances)
        );
        config.addFloorSpecs(specs);

        CarParkAssembler assembler = new CarParkAssembler();
        return assembler.assemble(config);
    }

    private int[] buildEntrancesArray() {
        int[] arr = new int[TOTAL_FLOORS];
        for (int i = 0; i < TOTAL_FLOORS; i++) {
            arr[i] = ENTRANCES_PER_FLOOR;
        }
        return arr;
    }
}

