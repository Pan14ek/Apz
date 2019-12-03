package ua.nure.apz.makieiev.apz.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {

	private String title;
	private String description;
	private String imageLink;
	private String status;
	private int score;
	private long idEvent;

}