package br.com.leandrojacomelli.marsexplorer.common.event;

import br.com.leandrojacomelli.marsexplorer.common.GenericId;

import java.io.Serializable;

public abstract class DomainEvent<T extends GenericId> implements Serializable {

    private final T aggregateId;
    private final int version;
    private final long timestamp;

    protected DomainEvent(T aggregateId, int version, long timestamp) {
        this.aggregateId = aggregateId;
        this.version = version;
        this.timestamp = timestamp;
    }

    public T getAggregateId() {
        return aggregateId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainEvent<?> that = (DomainEvent<?>) o;

        if (version != that.version) return false;
        if (timestamp != that.timestamp) return false;
        return aggregateId.equals(that.aggregateId);

    }

    @Override
    public int hashCode() {
        int result = aggregateId.hashCode();
        result = 31 * result + version;
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "DomainEvent{" +
                "aggregateId=" + aggregateId +
                ", version=" + version +
                ", timestamp=" + timestamp +
                '}';
    }
}
