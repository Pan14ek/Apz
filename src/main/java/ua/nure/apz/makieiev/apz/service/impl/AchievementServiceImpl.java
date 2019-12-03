package ua.nure.apz.makieiev.apz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.nure.apz.makieiev.apz.exception.notunique.NotUniqueAchievementException;
import ua.nure.apz.makieiev.apz.model.entity.Achievement;
import ua.nure.apz.makieiev.apz.repository.AchievementRepository;
import ua.nure.apz.makieiev.apz.service.AchievementService;

import java.util.Optional;

@Service
public class AchievementServiceImpl implements AchievementService {

	private AchievementRepository achievementRepository;

	@Autowired
	public AchievementServiceImpl(AchievementRepository achievementRepository) {
		this.achievementRepository = achievementRepository;
	}

	@Override
	public Achievement add(Achievement achievement) {
		try {
			return achievementRepository.save(achievement);
		} catch (DataIntegrityViolationException ex) {
			throw new NotUniqueAchievementException("The database contains a achievement with this title");
		}
	}

	@Override
	public Achievement update(Achievement achievement) {
		return achievementRepository.save(achievement);
	}

	@Override
	public Optional<Achievement> getById(long id) {
		return achievementRepository.findById(id);
	}

	@Override
	public Optional<Achievement> getByTitle(String title) {
		return achievementRepository.findByTitle(title);
	}

	@Override
	public boolean removeById(long id) {
		achievementRepository.deleteById(id);
		return true;
	}

}