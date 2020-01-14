package  task10_transportation_generics.common.business.repo;

import  task10_transportation_generics.common.business.domain.BaseEntity;

public final class CommonRepoHelper {

    private CommonRepoHelper() {

    }

    public static Integer findEntityIndexInArrayStorageById(BaseEntity[] data, long entityId) {
        for (int i = 0; i < data.length; i++) {
            if (Long.valueOf(entityId).equals(data[i].getId())) {
                return i;
            }
        }

        return null;
    }
}
