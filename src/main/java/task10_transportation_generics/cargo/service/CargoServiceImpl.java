package  task10_transportation_generics.cargo.service;

import  task10_transportation_generics.cargo.domain.Cargo;
import  task10_transportation_generics.cargo.exception.unckecked.CargoDeleteConstraintViolationException;
import  task10_transportation_generics.cargo.repo.CargoRepo;
import  task10_transportation_generics.cargo.search.CargoSearchCondition;
import  task10_transportation_generics.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CargoServiceImpl implements CargoService {

    private CargoRepo cargoRepo;

    public CargoServiceImpl(CargoRepo cargoRepo) {
        this.cargoRepo = cargoRepo;
    }

    @Override
    public void add(Cargo cargo) {
        cargoRepo.add(cargo);
    }

    @Override
    public Cargo getById(Long id) {
        if (id != null) {
            return cargoRepo.getById(id);
        }
        return null;
    }

    @Override
    public Cargo getByIdFetchingTransportations(Long id) {
        if (id != null) {
            return cargoRepo.getByIdFetchingTransportations(id);
        }
        return null;
    }

    @Override
    public List<Cargo> getAll() {
        return cargoRepo.getAll();
    }

    @Override
    public List<Cargo> findByName(String name) {
        Cargo[] found = cargoRepo.findByName(name);
        return (found == null || found.length == 0) ? Collections.emptyList() : Arrays.asList(found);
    }

    @Override
    public boolean deleteById(Long id) {
        Cargo cargo = this.getByIdFetchingTransportations(id);

        if (cargo != null) {
            List<Transportation> transportations = cargo.getTransportations();
            boolean hasTransportations = transportations != null && transportations.size() > 0;
            if (hasTransportations) {
                throw new CargoDeleteConstraintViolationException(id);
            }

            return cargoRepo.deleteById(id);
        } else {
            return false;
        }
    }

    @Override
    public void printAll() {
        List<Cargo> allCargos = cargoRepo.getAll();

        for (Cargo cargo : allCargos) {
            System.out.println(cargo);
        }
    }

    @Override
    public void update(Cargo cargo) {
        if (cargo != null) {
            cargoRepo.update(cargo);
        }
    }

    @Override
    public List<Cargo> search(CargoSearchCondition cargoSearchCondition) {
        return cargoRepo.search(cargoSearchCondition);
    }
}
