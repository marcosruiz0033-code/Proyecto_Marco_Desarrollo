package com.PC.Store.SistemaWeb.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String recurso, Integer id) {
        super(recurso + " con id " + id + " no encontrado");
    }
}
