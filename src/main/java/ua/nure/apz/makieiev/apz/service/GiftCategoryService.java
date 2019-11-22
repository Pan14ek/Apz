package ua.nure.apz.makieiev.apz.service;

import ua.nure.apz.makieiev.apz.model.entity.GiftCategory;

import java.util.Optional;

public interface GiftCategoryService {

    GiftCategory add(GiftCategory giftCategory);

    GiftCategory update(GiftCategory giftCategory);

    Optional<GiftCategory> getById(long id);

    Optional<GiftCategory> getByTitle(String title);

    boolean removeById(long id);

}
