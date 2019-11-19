package ua.nure.apz.makieiev.apz.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "events")
public class Event implements Serializable {

    private static final long serialVersionUID = 1317053787871639531L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private long id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Date_start")
    private LocalDateTime startDate;

    @Column(name = "Date_finish")
    private LocalDateTime finishDate;

    @Column(name = "Description")
    private String description;

}