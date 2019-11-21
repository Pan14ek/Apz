package ua.nure.apz.makieiev.apz.exception.notunique;

import ua.nure.apz.makieiev.apz.exception.AppException;

public class NotUniqueAchievementException extends AppException {

    private static final long serialVersionUID = 473407893985123059L;

    public NotUniqueAchievementException(String message) {
        super(message);
    }

    public NotUniqueAchievementException(String message, Throwable cause) {
        super(message, cause);
    }

}