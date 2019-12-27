package main.java.Task7_TransportationWithCollections.cargo.service.impl;

import main.java.Task7_TransportationWithCollections.cargo.domain.Cargo;
import main.java.Task7_TransportationWithCollections.cargo.repo.CargoRepo;
import main.java.Task7_TransportationWithCollections.cargo.service.CargoService;

public class CargoServiceImpl implements CargoService {

    private final CargoRepo cargoRepo;

    public CargoServiceImpl(CargoRepo cargoRepo) {
        this.cargoRepo = cargoRepo;
    }

    @Override
    public void add(Cargo cargo) {
        if (cargo != null) {
            cargoRepo.add(cargo);
            System.out.println("Cargo was added:\n" + cargo.toString());
        } else {
            System.out.println("Cargo was not added. Reason: cargo is null.");
        }
    }

    @Override
    public Cargo getById(Long id) {
        if (id != null) {
            return cargoRepo.getById(id);
        }

        System.out.println("Warning: Cargo was not found: id is null.");
        return null;
    }

    @Override
    public Cargo[] getByName(String name) {
        if (name == null) {
            System.out.println("Warning: name is null.");
        }
        return cargoRepo.getByName(name);
    }

    @Override
    public boolean deleteById(Long id) {
        if (id != null) {
            if (cargoRepo.deleteById(id)) {
                System.out.println("Cargo was deleted.");
                return true;
            }
            System.out.println("Error while deleting cargo: cargo was not found.");
        } else {
            System.out.println("Error while deleting cargo: id is null.");
        }
        return false;
    }

}
