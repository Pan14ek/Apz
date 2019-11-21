package ua.nure.apz.makieiev.apz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniqueGiftException;
import ua.nure.apz.makieiev.apz.model.Gift;
import ua.nure.apz.makieiev.apz.repository.GiftRepository;
import ua.nure.apz.makieiev.apz.service.GiftService;

import java.util.Optional;

@Service
public class GiftServiceImpl implements GiftService {

    private GiftRepository giftRepository;

    @Autowired
    public GiftServiceImpl(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    @Override
    public Gift add(Gift gift) {
        try {
            return giftRepository.save(gift);
        } catch (DataIntegrityViolationException ex) {
            throw new NotUniqueGiftException("The database contains a gift with this title");
        }
    }

    @Override
    public Gift update(Gift gift) {
        return giftRepository.save(gift);
    }

    @Override
    public Optional<Gift> getById(long id) {
        return giftRepository.findById(id);
    }

    @Override
    public Optional<Gift> getByTitle(String title) {
        return giftRepository.findByTitle(title);
    }

    @Override
    public boolean removeById(long id) {
        giftRepository.deleteById(id);
        return true;
    }

}