package  task9_transportation_exceptions.cargo.service.impl;

import  task9_transportation_exceptions.cargo.domain.Cargo;
import  task9_transportation_exceptions.cargo.exception.CargoDeleteException;
import  task9_transportation_exceptions.cargo.repo.CargoRepo;
import  task9_transportation_exceptions.cargo.search.CargoSearchCondition;
import  task9_transportation_exceptions.cargo.service.CargoService;
import  task9_transportation_exceptions.common.solutions.exception.TransportationException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static  task9_transportation_exceptions.common.solutions.exception.ExceptionMessage.CARGO_IS_USED_IN_TRANSPORTATION_ERROR;

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

        throw new CargoDeleteException(CARGO_DELETE_ERROR_CODE, CARGO_IS_USED_IN_TRANSPORTATION_ERROR);
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
