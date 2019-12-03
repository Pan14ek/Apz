package ua.nure.apz.makieiev.apz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.apz.makieiev.apz.model.statistic.EventStatistic;
import ua.nure.apz.makieiev.apz.repository.EventStatisticRepository;
import ua.nure.apz.makieiev.apz.service.EventStatisticService;

import java.util.List;

@Service
public class EventStatisticServiceImpl implements EventStatisticService {

	private EventStatisticRepository eventStatisticRepository;

	@Autowired
	public EventStatisticServiceImpl(EventStatisticRepository eventStatisticRepository) {
		this.eventStatisticRepository = eventStatisticRepository;
	}

	@Override
	public List<EventStatistic> getAllEventStatistics() {
		return eventStatisticRepository.getAllEventStatistics();
	}

}