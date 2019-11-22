package ua.nure.apz.makieiev.apz.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.nure.apz.makieiev.apz.model.statistic.EventStatistic;
import ua.nure.apz.makieiev.apz.repository.EventStatisticRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventStatisticsRepositoryImpl implements EventStatisticRepository {

    private static final String GET_ALL_EVENT_STATISTIC_SQL = "SELECT events.title as EventTitle,\n" +
            "COUNT(DISTINCT user.id_user) as UserAmount,\n" +
            "COUNT(DISTINCT task.Id_task) as TaskAmount \n" +
            "FROM events \n" +
            "INNER JOIN usersevents ON usersevents.Id_event = events.Id_event \n" +
            "INNER JOIN users user ON user.Id_user = usersevents.Id_user \n" +
            "INNER JOIN tasks task ON task.Id_event = events.Id_event;";

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
        });
        return eventStatistics;
    }

}