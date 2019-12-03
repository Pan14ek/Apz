package ua.nure.apz.makieiev.apz.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "positions")
public class Position implements Serializable {

	private static final long serialVersionUID = -9078571099374308233L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_position")
	private long id;

	@Column(name = "Title")
	private String title;

	@Column(name = "Description")
	private String description;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_position")
	@JsonManagedReference
	private User user;

	public Position(long id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}

}