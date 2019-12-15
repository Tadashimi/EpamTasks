package Task6_TransportationWithRepoAndService.cargo.repo;

import Task6_TransportationWithRepoAndService.cargo.domain.Cargo;
import Task6_TransportationWithRepoAndService.common.utils.ArrayUtils;
import Task6_TransportationWithRepoAndService.storage.Storage;

public class CargoRepoImpl implements CargoRepo {
    @Override
    public void addCargo(Cargo cargo) {
        Storage.addCargo(cargo);
    }

    @Override
    public Cargo getById(long id) {
        return Storage.getCargoById(id);
    }

    @Override
    public Cargo[] getByName(String name) {
        Cargo[] result = Storage.getCargosByName(name);
        ArrayUtils.trimArray(result);
        return result;
    }

    @Override
    public void deleteById(long id) {
        Storage.deleteCargoById(id);
    }
}
