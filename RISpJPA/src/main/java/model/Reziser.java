package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the reziser database table.
 * 
 */
@Entity
@NamedQuery(name="Reziser.findAll", query="SELECT r FROM Reziser r")
public class Reziser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idReziser;

	private String ime;

	private String nadimak;

	private String prezime;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="reziser")
	private List<Film> films;

	//bi-directional many-to-many association to Korisnik
	@ManyToMany
	@JoinTable(
		name="korisnik_dislike_reziser"
		, joinColumns={
			@JoinColumn(name="Reziser_idReziser")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Korisnik_idKorisnik")
			}
		)
	private List<Korisnik> dislikedByKorisnik;

	//bi-directional many-to-many association to Korisnik
	@ManyToMany
	@JoinTable(
		name="korisnik_faves_reziser"
		, joinColumns={
			@JoinColumn(name="Reziser_idReziser")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Korisnik_idKorisnik")
			}
		)
	private List<Korisnik> favedByKorisnik;

	//bi-directional many-to-many association to Korisnik
	@ManyToMany
	@JoinTable(
		name="korisnik_like_reziser"
		, joinColumns={
			@JoinColumn(name="Reziser_idReziser")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Korisnik_idKorisnik")
			}
		)
	private List<Korisnik> likedByKorisnik;

	public Reziser() {
	}

	public int getIdReziser() {
		return this.idReziser;
	}

	public void setIdReziser(int idReziser) {
		this.idReziser = idReziser;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getNadimak() {
		return this.nadimak;
	}

	public void setNadimak(String nadimak) {
		this.nadimak = nadimak;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<Film> getFilms() {
		return this.films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public Film addFilm(Film film) {
		getFilms().add(film);
		film.setReziser(this);

		return film;
	}

	public Film removeFilm(Film film) {
		getFilms().remove(film);
		film.setReziser(null);

		return film;
	}

	public List<Korisnik> getDislikedByKorisnik() {
		return this.dislikedByKorisnik;
	}

	public void setDislikedByKorisnik(List<Korisnik> dislikedByKorisnik) {
		this.dislikedByKorisnik = dislikedByKorisnik;
	}

	public List<Korisnik> getFavedByKorisnik() {
		return this.favedByKorisnik;
	}

	public void setFavedByKorisnik(List<Korisnik> favedByKorisnik) {
		this.favedByKorisnik = favedByKorisnik;
	}

	public List<Korisnik> getLikedByKorisnik() {
		return this.likedByKorisnik;
	}

	public void setLikedByKorisnik(List<Korisnik> likedByKorisnik) {
		this.likedByKorisnik = likedByKorisnik;
	}

	@Override
	public String toString() {
		if(nadimak.isEmpty()) {
			return  ime + " " + prezime ;
		}else {
			return  ime + " (" + nadimak + ") " + prezime ;
		}
		
		
	}

	
	
	
	
}