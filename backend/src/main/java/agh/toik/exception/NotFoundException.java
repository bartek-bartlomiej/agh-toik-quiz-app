package agh.toik.exception;

import javax.annotation.Generated;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-26T17:58:06.365Z[GMT]")
public class NotFoundException extends ApiException {
    private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
