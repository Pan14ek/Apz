package ua.nure.apz.makieiev.apz.service;

import ua.nure.apz.makieiev.apz.model.Gift;

import java.util.Optional;

public interface GiftService {

    Gift add(Gift gift);

    Gift update(Gift gift);

    Optional<Gift> getById(long id);

    Optional<Gift> getByTitle(String title);

    boolean removeById(long id);

}
