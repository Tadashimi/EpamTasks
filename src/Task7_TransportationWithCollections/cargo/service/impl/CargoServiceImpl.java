package Task7_TransportationWithCollections.cargo.service.impl;

import Task7_TransportationWithCollections.cargo.domain.Cargo;
import Task7_TransportationWithCollections.cargo.repo.impl.CargoCollectionRepoImpl;
import Task7_TransportationWithCollections.cargo.service.CargoService;

import java.util.List;

public class CargoServiceImpl implements CargoService {

    private final CargoCollectionRepoImpl cargoCollectionRepo;

    public CargoServiceImpl() {
        cargoCollectionRepo = new CargoCollectionRepoImpl();
    }

    public CargoServiceImpl(CargoCollectionRepoImpl cargoCollectionRepo) {
        this.cargoCollectionRepo = cargoCollectionRepo;
    }

    @Override
    public void add(Cargo cargo) {
        if (cargo != null) {
            cargoCollectionRepo.add(cargo);
            System.out.println("Cargo was added:\n" + cargo.toString());
        } else {
            System.out.println("Cargo was not added. Reason: cargo is null.");
        }
    }

    @Override
    public Cargo getById(Long id) {
        if (id != null) {
            return cargoCollectionRepo.getById(id);
        }

        System.out.println("Warning: Cargo was not found: id is null.");
        return null;
    }

    @Override
    public List<Cargo> getByName(String name) {
        if (name == null) {
            System.out.println("Warning: name is null.");
        }
        return cargoCollectionRepo.getByName(name);
    }

    @Override
    public boolean deleteById(Long id) {
        if (id != null) {
            if (cargoCollectionRepo.deleteById(id)) {
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
