package com.laboratorios.labs.controllers.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public class StandardErro implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Instant timestamp;
    private Integer status;
    private String error;
    private String messenger;
    private String path;

    public StandardErro() {
    }

    public StandardErro(Instant timestamp, Integer status, String error, String messenger, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.messenger = messenger;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }
}
