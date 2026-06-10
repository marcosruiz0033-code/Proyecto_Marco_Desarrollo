package com.PC.Store.SistemaWeb.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String mensaje,
        List<String> errores,
        LocalDateTime timestamp
) {
    public ErrorResponse(int status, String mensaje) {
        this(status, mensaje, null, LocalDateTime.now());
    }

    public ErrorResponse(int status, String mensaje, List<String> errores) {
        this(status, mensaje, errores, LocalDateTime.now());
    }
}
