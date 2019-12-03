package ua.nure.apz.makieiev.apz.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskAchievement {

	private long id;
	private Achievement achievement;
	private Task task;
	private User user;
	private LocalDateTime getAchievementDate;

}
