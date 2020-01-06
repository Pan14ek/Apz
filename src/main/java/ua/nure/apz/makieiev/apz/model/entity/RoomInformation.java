package ua.nure.apz.makieiev.apz.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "room_climate")
public class RoomInformation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_room_information")
	private long id;

	@Column(name = "Temperature")
	private double temperature;

	@Column(name = "Humidity")
	private double humidity;

	@JsonIgnore
	@Column(name = "Scan_date")
	private LocalDateTime scanDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Id_room")
	@JsonBackReference
	private Room room;

}
