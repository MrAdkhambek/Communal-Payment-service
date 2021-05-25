package r2.llc.communal.util;

import lombok.Value;


@Value
public class ResponseAPI<T> {
    String message;
    T data;
}
