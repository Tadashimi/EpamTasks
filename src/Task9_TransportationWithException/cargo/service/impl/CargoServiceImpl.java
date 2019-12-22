package Task9_TransportationWithException.cargo.service.impl;

import Task9_TransportationWithException.cargo.domain.Cargo;
import Task9_TransportationWithException.cargo.exception.CargoDeleteException;
import Task9_TransportationWithException.cargo.repo.CargoRepo;
import Task9_TransportationWithException.cargo.search.CargoSearchCondition;
import Task9_TransportationWithException.cargo.service.CargoService;
import Task9_TransportationWithException.common.solutions.exception.TransportationException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static Task9_TransportationWithException.common.solutions.exception.ExceptionMessage.CARGO_IS_USED_IN_TRANSPORTATION;

public class CargoServiceImpl implements CargoService {

    private CargoRepo cargoRepo;
    private final int CARGO_DELETE_ERROR_CODE = 99;

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
    public List<Cargo> getAll() {
        return cargoRepo.getAll();
    }

    @Override
    public List<Cargo> findByName(String name) {
        Cargo[] found = cargoRepo.findByName(name);
        return (found == null || found.length == 0) ? Collections.emptyList() : Arrays.asList(found);
    }

    @Override
    public boolean deleteById(Long id) throws TransportationException {

        int cargoTransportationCount = cargoRepo.getById(id).getTransportations().size();

        if (cargoTransportationCount == 0) {
            return cargoRepo.deleteById(id);
        }

        throw new CargoDeleteException(CARGO_DELETE_ERROR_CODE, CARGO_IS_USED_IN_TRANSPORTATION);
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
