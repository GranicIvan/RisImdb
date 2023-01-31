package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	private String broj_telefona;

	private String ime;

	private String mail;

	private String prezime;

	private String sifra;

	//bi-directional many-to-many association to Film
	@ManyToMany(mappedBy="dislikedByKorisnik")
	private List<Film> dislikesFilm;

	//bi-directional many-to-many association to Film
	@ManyToMany(mappedBy="favedByKorisnik")
	private List<Film> favesFilm;

	//bi-directional many-to-many association to Film
	@ManyToMany(mappedBy="likedByKorisnik")
	private List<Film> likesFilm;

	//bi-directional many-to-many association to Glumac
	@ManyToMany
	@JoinTable(
		name="korisnik_dislike_glumac"
		, joinColumns={
			@JoinColumn(name="Korisnik_idKorisnik")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Glumac_idGlumac")
			}
		)
	private List<Glumac> dislikesGlumac;

	//bi-directional many-to-many association to Glumac
	@ManyToMany
	@JoinTable(
		name="korisnik_faves_glumac"
		, joinColumns={
			@JoinColumn(name="Korisnik_idKorisnik")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Glumac_idGlumac")
			}
		)
	private List<Glumac> favesGlumac;

	//bi-directional many-to-many association to Glumac
	@ManyToMany
	@JoinTable(
		name="korisnik_like_glumac"
		, joinColumns={
			@JoinColumn(name="Korisnik_idKorisnik")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Glumac_idGlumac")
			}
		)
	private List<Glumac> likesGlumac;

	//bi-directional many-to-many association to Reziser
	@ManyToMany(mappedBy="dislikedByKorisnik")
	private List<Reziser> dislikesReziser;

	//bi-directional many-to-many association to Reziser
	@ManyToMany(mappedBy="favedByKorisnik")
	private List<Reziser> favesReziser;

	//bi-directional many-to-many association to Reziser
	@ManyToMany(mappedBy="likedByKorisnik")
	private List<Reziser> likesReziser;

	//bi-directional many-to-many association to Uloga
	@ManyToMany(mappedBy="korisniks", fetch = FetchType.EAGER )
	private List<Uloga> ulogas;

	public Korisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getBroj_telefona() {
		return this.broj_telefona;
	}

	public void setBroj_telefona(String broj_telefona) {
		this.broj_telefona = broj_telefona;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getSifra() {
		return this.sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public List<Film> getDislikesFilm() {
		return this.dislikesFilm;
	}

	public void setDislikesFilm(List<Film> dislikesFilm) {
		this.dislikesFilm = dislikesFilm;
	}

	public List<Film> getFavesFilm() {
		return this.favesFilm;
	}

	public void setFavesFilm(List<Film> favesFilm) {
		this.favesFilm = favesFilm;
	}

	public List<Film> getLikesFilm() {
		return this.likesFilm;
	}

	public void setLikesFilm(List<Film> likesFilm) {
		this.likesFilm = likesFilm;
	}

	public List<Glumac> getDislikesGlumac() {
		return this.dislikesGlumac;
	}

	public void setDislikesGlumac(List<Glumac> dislikesGlumac) {
		this.dislikesGlumac = dislikesGlumac;
	}

	public List<Glumac> getFavesGlumac() {
		return this.favesGlumac;
	}

	public void setFavesGlumac(List<Glumac> favesGlumac) {
		this.favesGlumac = favesGlumac;
	}

	public List<Glumac> getLikesGlumac() {
		return this.likesGlumac;
	}

	public void setLikesGlumac(List<Glumac> likesGlumac) {
		this.likesGlumac = likesGlumac;
	}

	public List<Reziser> getDislikesReziser() {
		return this.dislikesReziser;
	}

	public void setDislikesReziser(List<Reziser> dislikesReziser) {
		this.dislikesReziser = dislikesReziser;
	}

	public List<Reziser> getFavesReziser() {
		return this.favesReziser;
	}

	public void setFavesReziser(List<Reziser> favesReziser) {
		this.favesReziser = favesReziser;
	}

	public List<Reziser> getLikesReziser() {
		return this.likesReziser;
	}

	public void setLikesReziser(List<Reziser> likesReziser) {
		this.likesReziser = likesReziser;
	}

	public List<Uloga> getUlogas() {
		return this.ulogas;
	}

	public void setUlogas(List<Uloga> ulogas) {
		this.ulogas = ulogas;
	}

	public void addUloga(Uloga role) {
		ulogas.add(role);
		
	}

}