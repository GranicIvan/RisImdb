package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the glumac database table.
 * 
 */
@Entity
@NamedQuery(name="Glumac.findAll", query="SELECT g FROM Glumac g")
public class Glumac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idGlumac;

	@Temporal(TemporalType.DATE)
	@Column(name = "aktivan_do")
	private Date aktivanDo;

	@Temporal(TemporalType.DATE)
	@Column(name = "aktivan_od")
	private Date aktivanOd;

	private int godiste;

	private String ime;

	private String nadimak;

	private String prezime;

	//bi-directional many-to-many association to Film
	@ManyToMany(mappedBy="glumacs")
	private List<Film> films;

	//bi-directional many-to-many association to Korisnik
	@ManyToMany(mappedBy="dislikesGlumac")
	private List<Korisnik> dislikedByKorisnik;

	//bi-directional many-to-many association to Korisnik
	@ManyToMany(mappedBy="favesGlumac")
	private List<Korisnik> favedByKorisnik;

	//bi-directional many-to-many association to Korisnik
	@ManyToMany(mappedBy="likesGlumac")
	private List<Korisnik> likedByKorisnik;

	public Glumac() {
	}

	public int getIdGlumac() {
		return this.idGlumac;
	}

	public void setIdGlumac(int idGlumac) {
		this.idGlumac = idGlumac;
	}

	public Date getAktivan_do() {
		return this.aktivanDo;
	}

	public void setAktivan_do(Date aktivan_do) {
		this.aktivanDo = aktivan_do;
	}

	public Date getAktivan_od() {
		return this.aktivanOd;
	}

	public void setAktivan_od(Date aktivan_od) {
		this.aktivanOd = aktivan_od;
	}

	public int getGodiste() {
		return this.godiste;
	}

	public void setGodiste(int godiste) {
		this.godiste = godiste;
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

}