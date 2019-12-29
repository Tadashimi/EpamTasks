package Task8_TransportationWithUpdateAndCompare.cargo.service.impl;

import Task8_TransportationWithUpdateAndCompare.cargo.domain.Cargo;
import Task8_TransportationWithUpdateAndCompare.cargo.repo.CargoRepo;
import Task8_TransportationWithUpdateAndCompare.cargo.service.CargoService;
import Task8_TransportationWithUpdateAndCompare.cargo.service.cargoListComparator.CargoSort;
import Task8_TransportationWithUpdateAndCompare.cargo.service.cargoListComparator.CompareField;
import Task8_TransportationWithUpdateAndCompare.common.solutions.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public List<Cargo> getByName(String name) {
        if (name == null) {
            System.out.println("Warning: name is null.");
        }
        Cargo[] found = cargoRepo.getByName(name);
        return (found == null || found.length == 0) ? Collections.emptyList() : Arrays.asList(found);
    }

    @Override
    public List<Cargo> sortCargo(CompareField compareField) {
        List<Cargo> initialCargoList = new ArrayList<>(Arrays.asList(cargoRepo.getAll()));
        CargoSort.sortCargoList(initialCargoList, compareField);
        return initialCargoList;
    }

    @Override
    public Cargo update(Long id, Cargo cargo) {
        if (id == null) {
            System.out.println("Error while updating cargo: id is null.");
        } else if (cargo == null) {
            System.out.println("Error while updating cargo: cargo is null.");
        } else {
            return cargoRepo.update(id, cargo);
        }
        return null;
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

    @Override
    public void printAll() {
        Cargo[] cargos = cargoRepo.getAll();
        if (cargos != null && cargos.length != 0) {
            ArrayUtils.printArray(cargos);
        } else {
            System.out.println("There is no any cargo in repo.");
        }
    }

}
