package ua.nure.apz.makieiev.apz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniqueGiftCategoryException;
import ua.nure.apz.makieiev.apz.model.entity.GiftCategory;
import ua.nure.apz.makieiev.apz.repository.GiftCategoryRepository;
import ua.nure.apz.makieiev.apz.service.GiftCategoryService;

import java.util.Optional;

@Service
public class GiftCategoryServiceImpl implements GiftCategoryService {

    private GiftCategoryRepository giftCategoryRepository;

    @Autowired
    public GiftCategoryServiceImpl(GiftCategoryRepository giftCategoryRepository) {
        this.giftCategoryRepository = giftCategoryRepository;
    }

    @Override
    public GiftCategory add(GiftCategory giftCategory) {
        try {
            return giftCategoryRepository.save(giftCategory);
        } catch (DataIntegrityViolationException ex) {
            throw new NotUniqueGiftCategoryException("The database contains a gift category with this title");
        }
    }

    @Override
    public GiftCategory update(GiftCategory giftCategory) {
        return giftCategoryRepository.save(giftCategory);
    }

    @Override
    public Optional<GiftCategory> getById(long id) {
        return giftCategoryRepository.findById(id);
    }

    @Override
    public Optional<GiftCategory> getByTitle(String title) {
        return giftCategoryRepository.findByTitle(title);
    }

    @Override
    public boolean removeById(long id) {
        giftCategoryRepository.deleteById(id);
        return true;
    }

}