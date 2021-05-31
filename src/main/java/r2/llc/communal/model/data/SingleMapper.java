package r2.llc.communal.model.data;

public interface SingleMapper<INPUT, OUTPUT> {
    OUTPUT map(INPUT value);
}
