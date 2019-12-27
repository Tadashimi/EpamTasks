package Task11_TransportationWithFileReader.common.business.domain;

public abstract class BaseEntity<ID> {

    protected ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

}
