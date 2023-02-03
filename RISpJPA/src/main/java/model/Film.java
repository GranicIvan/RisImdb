package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idFilm;
	
	private String ime;

	@Temporal(TemporalType.DATE)
	private Date release_date;

	private int trajanje;

	// OVO JE POLUPANO -----------------------------------------------
	//bi-directional many-to-many association to Glumac
	@ManyToMany
	@JoinTable(
		name="glumac_glumi_u_film"
		, joinColumns={
			@JoinColumn(name="Film_idFilm")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Glumac_idGlumac")
			}
		)
	private List<Glumac> glumacs;

	//bi-directional many-to-many association to Korisnik
	@ManyToMany
	@JoinTable(
		name="korisnik_dislike_film"
		, joinColumns={
			@JoinColumn(name="Film_idFilm")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Korisnik_idKorisnik")
			}
		)
	private List<Korisnik> dislikedByKorisnik;

	//bi-directional many-to-many association to Korisnik
	@ManyToMany
	@JoinTable(
		name="korisnik_faves_film"
		, joinColumns={								// ovo obrises
			@JoinColumn(name="Film_idFilm")
			}
		, inverseJoinColumns={//ova dva zamenis
			@JoinColumn(name="Korisnik_idKorisnik")
			}
		)
	private List<Korisnik> favedByKorisnik;

	//bi-directional many-to-many association to Korisnik
	@ManyToMany
	@JoinTable(
		name="korisnik_like_film"
		, joinColumns={
			@JoinColumn(name="Film_idFilm")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Korisnik_idKorisnik")
			}
		)
	private List<Korisnik> likedByKorisnik;

	//bi-directional many-to-one association to Reziser
	@ManyToOne
	private Reziser reziser;

	//bi-directional many-to-one association to Zanr
	@ManyToOne
	@JoinColumn(name="Zanr_idZanr")
	private Zanr glavniZanr;

	//bi-directional many-to-many association to Zanr
	@ManyToMany
	@JoinTable(
		name="film_has_zanr"
		, joinColumns={
			@JoinColumn(name="Film_idFilm")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Zanr_idZanr")
			}
		)
	private List<Zanr> hasZanr;

	public Film() {
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Date getRelease_date() {
		return this.release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public int getTrajanje() {
		return this.trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public List<Glumac> getGlumacs() {
		return this.glumacs;
	}

	public void setGlumacs(List<Glumac> glumacs) {
		this.glumacs = glumacs;
	}
	
	public void addGlumac(Glumac g) {
		this.glumacs.add(g);
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
	
	public void addFavedByKorisnik(Korisnik k) {
		this.favedByKorisnik.add(k);
	}

	public List<Korisnik> getLikedByKorisnik() {
		return this.likedByKorisnik;
	}

	public void setLikedByKorisnik(List<Korisnik> likedByKorisnik) {
		this.likedByKorisnik = likedByKorisnik;
	}

	public Reziser getReziser() {
		return this.reziser;
	}

	public void setReziser(Reziser reziser) {
		this.reziser = reziser;
	}

	public Zanr getGlavniZanr() {
		return this.glavniZanr;
	}
	
	public int getGlavniZanrId() {
		return this.glavniZanr.getIdZanr();
	}

	public void setGlavniZanr(Zanr glavniZanr) {
		this.glavniZanr = glavniZanr;
	}

	public List<Zanr> getHasZanr() {
		return this.hasZanr;
	}

	public void setHasZanr(List<Zanr> hasZanr) {
		this.hasZanr = hasZanr;
	}

	@Override
	public String toString() {
		return "idFilm=" + idFilm + ", ime=" + ime + ", release_date=" + release_date + ", trajanje=" + trajanje
				+ ", reziser=" + reziser + ", glavniZanr=" + glavniZanr ;
	}

	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

	
	
	
}