package r2.llc.communal.model.data;

public interface EntityMapper<T, R> {

    T mapRT(R from);

    R mapTR(T from);
}
