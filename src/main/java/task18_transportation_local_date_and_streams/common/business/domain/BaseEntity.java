package task18_transportation_local_date_and_streams.common.business.domain;

import java.io.Serializable;

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
