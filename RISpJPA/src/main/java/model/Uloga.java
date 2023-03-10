package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the uloga database table.
 * 
 */
@Entity
@NamedQuery(name="Uloga.findAll", query="SELECT u FROM Uloga u")
public class Uloga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUloga;

	private String ime;

	//bi-directional many-to-many association to Korisnik
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="korisnik_has_uloga"
		, joinColumns={
			@JoinColumn(name="Uloga_idUloga")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Korisnik_idKorisnik")
			}
		)
	private List<Korisnik> korisniks;

	public Uloga() {
	}

	public int getIdUloga() {
		return this.idUloga;
	}

	public void setIdUloga(int idUloga) {
		this.idUloga = idUloga;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public List<Korisnik> getKorisniks() {
		return this.korisniks;
	}

	public void setKorisniks(List<Korisnik> korisniks) {
		this.korisniks = korisniks;
	}

	public void addKorisnik(Korisnik k) {
		korisniks.add(k);
		
	}

}