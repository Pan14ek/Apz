package ua.nure.apz.makieiev.apz.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.nure.apz.makieiev.apz.model.statistic.EventStatistic;
import ua.nure.apz.makieiev.apz.repository.EventStatisticRepository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EventStatisticsRepositoryImpl implements EventStatisticRepository {

	private static final String GET_ALL_EVENT_STATISTIC_SQL = "SELECT events.title as EventTitle," +
			"COUNT(DISTINCT user.id_user) as UserAmount," +
			"COUNT(DISTINCT task.Id_task) as TaskAmount," +
			"(SELECT COUNT(DISTINCT taskachievements.Id_task) FROM taskachievements " +
			"INNER JOIN users on users.id_user = taskachievements.Id_user)/" +
			"COUNT(DISTINCT task.Id_task) as PercentOfDoneTask " +
			"FROM events " +
			"INNER JOIN usersevents ON usersevents.Id_event = events.Id_event " +
			"INNER JOIN users user ON user.Id_user = usersevents.Id_user " +
			"INNER JOIN tasks task ON task.Id_event = events.Id_event;";

	private static final String GET_RATING_SQL = "SELECT user.Login,SUM(task.Score) as TotalScore FROM tasks task " +
			"INNER JOIN taskachievements on task.id_task = taskachievements.Id_task " +
			"INNER  JOIN users user on taskachievements.Id_user = user.id_user;";

	private DataSource dataSource;

	@Autowired
	public EventStatisticsRepositoryImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<EventStatistic> getAllEventStatistics() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<EventStatistic> eventStatistics = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_ALL_EVENT_STATISTIC_SQL);
		rows.forEach(stringObjectMap -> {
			EventStatistic eventStatistic = new EventStatistic();
			eventStatistic.setEventTitle(String.valueOf(stringObjectMap.get("eventTitle")));
			eventStatistic.setTaskAmount(Long.parseLong(String.valueOf(stringObjectMap.get("TaskAmount"))));
			eventStatistic.setUserAmount(Long.parseLong(String.valueOf(stringObjectMap.get("UserAmount"))));
			eventStatistic.setPercentOfDoneTasks(BigDecimal.valueOf(Double.parseDouble(String.valueOf(stringObjectMap.get("PercentOfDoneTask")))));
			eventStatistics.add(eventStatistic);
		});
		return eventStatistics;
	}

}