package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the zanr database table.
 * 
 */
@Entity
@NamedQuery(name="Zanr.findAll", query="SELECT z FROM Zanr z")
public class Zanr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idZanr;

	private String ime;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="glavniZanr")
	private List<Film> films1;

	//bi-directional many-to-many association to Film
	@ManyToMany(mappedBy="hasZanr")
	private List<Film> films;

	public Zanr() {
	}

	public int getIdZanr() {
		return this.idZanr;
	}

	public void setIdZanr(int idZanr) {
		this.idZanr = idZanr;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public List<Film> getFilms1() {
		return this.films1;
	}

	public void setFilms1(List<Film> films1) {
		this.films1 = films1;
	}

	public Film addFilms1(Film films1) {
		getFilms1().add(films1);
		films1.setGlavniZanr(this);

		return films1;
	}

	public Film removeFilms1(Film films1) {
		getFilms1().remove(films1);
		films1.setGlavniZanr(null);

		return films1;
	}

	public List<Film> getFilms() {
		return this.films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

}