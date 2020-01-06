package ua.nure.apz.makieiev.apz.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bad_climate_room_relax_time")
public class BadClimateRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_bad_climate_room")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Id_room")
	private Room room;

	@Column(name = "Date_start")
	private LocalDateTime startDate;

	@Column(name = "Date_finish")
	private LocalDateTime finishDate;

}
