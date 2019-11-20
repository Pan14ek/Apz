package ua.nure.apz.makieiev.apz.service;

import ua.nure.apz.makieiev.apz.model.Achievement;

import java.util.Optional;

public interface AchievementService {

    Achievement add(Achievement achievement);

    Achievement update(Achievement achievement);

    Optional<Achievement> getById(long id);

    Optional<Achievement> getByTitle(String title);

    boolean removeById(long id);

}
