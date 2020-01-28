package task17_transportation_optional.common.business.domain;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 99L;

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
