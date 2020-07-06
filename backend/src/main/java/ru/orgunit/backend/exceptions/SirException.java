package ru.orgunit.backend.exceptions;

public class SirException extends RuntimeException {
    private SirErrorStatus status = SirErrorStatus.UNKNOWN;

    public SirException() {
        super();
    }

    public SirException(SirErrorStatus status, String message) {
        super(message);
        this.status = status;
    }

    public SirException(SirErrorStatus status) {
        super(status.getDescription());
        this.status = status;
    }

    public SirException(String message) {
        super(message);
    }

    public SirException(String message, Throwable cause) {
        super(message, cause);
    }

    public SirException(Throwable cause) {
        super(cause);
    }

    public SirErrorStatus getStatus() {
        return status;
    }

    public void setStatus(SirErrorStatus status) {
        this.status = status;
    }
}
