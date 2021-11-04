package com.example.bibliotekos_panaudojimas_3dalis.validator;

public class ValidatorResponse {
    public final String value;
    public final boolean status;

    public ValidatorResponse(boolean status, String value)
    {
        this.status = status;
        this.value = value;
    }
}
