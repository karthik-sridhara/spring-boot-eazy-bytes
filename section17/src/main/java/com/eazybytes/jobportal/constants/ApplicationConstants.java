package com.eazybytes.jobportal.constants;

public class ApplicationConstants {

    private ApplicationConstants() {
        throw new AssertionError("Utility class cannot be instantiated");
    }

    public static final String JWT_SECRET_KEY = "JWT_SECRET";
    public static final String JWT_HEADER = "Authorization";

    public static final String ROLE_JOB_SEEKER = "ROLE_JOB_SEEKER";

    public static final String NEW_CONTACT_MESSAGE_STATUS = "NEW";
    public static final String CLOSE_CONTACT_MESSAGE_STATUS = "CLOSED";
}
