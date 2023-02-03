package com.example.demo.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.FilmRepo;
import com.example.demo.repository.GlumacRepository;
import com.example.demo.repository.ReziserRepo;
import com.example.demo.repository.ZanrRepo;

import model.Film;
import model.Glumac;
import model.Korisnik;
import model.Reziser;
import model.Zanr;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value="/film")
public class FilmController {
	
	@Autowired
	FilmRepo fr;
	
	@Autowired
	ZanrRepo zr;
	
	@Autowired
	ReziserRepo rr;
	
	@Autowired
	GlumacRepository gr;
	
	
	@RequestMapping(value="/getZanrove", method=RequestMethod.GET)
	public String getZanrove(HttpServletRequest request) {
		List<Zanr> zan = zr.findAll();
		request.getSession().setAttribute("zanrovi", zan);
		return "film/getRezisere";
	}

	@RequestMapping(value="/getRezisere", method=RequestMethod.GET)
	public String getRezisere(HttpServletRequest request) {
		List<Reziser> rez = rr.findAll();
		request.getSession().setAttribute("reziseri", rez);
		return "unos/UnosFilma";
	}
	
	@RequestMapping(value="/getRezisereIZanrove", method=RequestMethod.GET)
	public String getRezisereIZanroce(HttpServletRequest request) {
		
		List<Zanr> zan = zr.findAll();
		request.getSession().setAttribute("zanrovi", zan);
		
		List<Reziser> rez = rr.findAll();
		request.getSession().setAttribute("reziseri", rez);
		
		return "unos/UnosFilma";
	}
	
	
	
	
	
//	@RequestMapping(value="/saveFilm", method=RequestMethod.POST)
//	public String saveFilm(@ModelAttribute("film") Film f,  Model m, HttpServletRequest request  ) {
//		
//		Film novi = fr.save(f);
//		request.getSession().setAttribute("film", f);
//		m.addAttribute("poruka", "Reziser je uspesno sacuvan.");
//		System.out.println("Reziser je sacuvan");
//		
//		return "unos/UnosFilma";
//		
//	}
	
	
	@RequestMapping(value="/saveFilm", method=RequestMethod.POST)
	public String saveFilm(String ime, Date release_date, Integer trajanje, Integer idZanr, Integer idReziser,   Model m, HttpServletRequest request  ) {
		
		
		Film f = new Film();
		
		f.setIme(ime);
		f.setRelease_date(release_date);
		f.setTrajanje(trajanje);
		
		Zanr z = zr.findById(idZanr).get();
		f.setGlavniZanr(z);
//		z.addFilms1(f); // DA LI OVO TREBA?
		
		Reziser r = rr.findById(idReziser).get();
		f.setReziser(r);
		
		
		Film novi = fr.save(f);
		request.getSession().setAttribute("film", f);
		m.addAttribute("poruka", "Film je uspesno sacuvan.");
//		System.out.println("Film: "+ novi +" je sacuvan");
		
//		Film ubacen = fr.findById(novi.get)
		
		return "unos/UnosFilma";
		
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	
	@RequestMapping(value="/getSveFilmove", method=RequestMethod.GET)
	public String getSveFilmove(HttpServletRequest request) {
		
		List<Film> filmovi =  fr.findAll();
		request.getSession().setAttribute("filmovi", filmovi);
		
		return "SviFilmovi";
	}
	
	@RequestMapping(value="/getSveFilmove2", method=RequestMethod.GET)
	public String getSveFilmove2(HttpServletRequest request) {
		
		List<Film> filmovi =  fr.findAll();
		request.getSession().setAttribute("filmovi", filmovi);
		
		return "SviFilmovi2";
	}
	
//	@RequestMapping(value="/faveFilm", method=RequestMethod.GET)
//	public String faveFilm(Integer idF, Integer idK, HttpServletRequest request) {
//		Film f =  fr.findById(idF).get();
//		
//		
//		return "";
//	}
	
	@RequestMapping(value="/filmUPerodu", method=RequestMethod.GET)
	public String filmUPerodu(Date datumOd, Date datumDo,  HttpServletRequest request) {
		
		List<Film> filmovi = fr.filmoviUPerioduODDO(datumOd, datumDo);
		request.getSession().setAttribute("filmovi", filmovi);
		
		
		return "FilmoviUPeriodu";
	}
	
	@RequestMapping(value="/sortiraniFilmovi", method=RequestMethod.GET)
	public String sortiraniFilmovi(HttpServletRequest request) {
		
		List<Film> filmovi = fr.sortiraniFilmoviR();
		System.out.println("PRINT " + filmovi.size());
		request.getSession().setAttribute("filmovi", filmovi);
		
		return "SortiraniFilmovi";
	}
	
	@RequestMapping(value="/getSviFilmoviReport.pdf", method=RequestMethod.GET)
	public void showReport(HttpServletResponse response) throws Exception{
//		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(fr.findAll());
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/SviFilmoviReport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("imeBiblioteke", "Biblioteka DMI");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=SviFilmovi.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
	}

	@RequestMapping(value="/faveFilm", method=RequestMethod.GET)
	public String faveFilm(Integer idFilm,  Model m, HttpServletRequest request  ) {
		System.out.println("Usao");
		
		Film film = fr.findById(idFilm).get();
		Korisnik korisnik = new Korisnik(); //Kako da ga dobijem
		
		film.addFavedByKorisnik(korisnik); 
		
		Principal principal = request.getUserPrincipal();
		String ime = principal.getName();
		
		System.out.println("ime je:" + ime);
		
		
		String path = request.getContextPath();
		System.out.println(path);
		
		return "SviFilmovi"; //ISTU STRANICU 
		//na jsp hidden input koji je adresa stranice i to uhvatimo u str parametar i to uradimo return
	}
		
	
	@RequestMapping(value="/dataForPoveziFiG", method=RequestMethod.GET)
	public String dataForPoveziFiG( HttpServletRequest request) {
		
		List<Film> filmovi = fr.findAll();
		request.getSession().setAttribute("filmovi", filmovi);
		
		List<Glumac> glumci = gr.findAll();
		request.getSession().setAttribute("glumci", glumci);
		
		return "povezivanje/PovezFilmGlumac";
	}
	
	
	@RequestMapping(value="/poveziFiG", method=RequestMethod.POST)//Bilo POST
	public String poveziFiG(Integer idFilm, Integer idGlumac,  Model m, HttpServletRequest request  ) {

		Film film = fr.findById(idFilm).get();
		Glumac glumac = gr.findById(idGlumac).get();
		
		
		film.addGlumac(glumac);	
		
		//TODO Ubaciti proveru da li su vec povezani, i ispisati poruku
		fr.save(film);
//		gr.save(glumac);
		
		
//		glumac.addFilm(film); // DA LI OVO TREBA? <-----
//		glumac.addFilm(film);
		
		
		return "povezivanje/PovezFilmGlumac";
	}
	
	
	@RequestMapping(value="/getRezisereZaJasper", method=RequestMethod.GET)
	public String getZanroveZaJasper(HttpServletRequest request) {
		List<Reziser> rez = rr.findAll();
		request.getSession().setAttribute("reziseri", rez);		
		return "IzaberiReziseraJasper";
	}
	
	
	@RequestMapping(value="/pozoviJasperFpZ", method=RequestMethod.POST)//bilo get
	public String pozoviJasperFpZ(Integer idReziser, HttpServletRequest request) {
		
		Reziser r = rr.findById(idReziser).get();
		request.getSession().setAttribute("imeRezisera", r.toString());
		request.getSession().setAttribute("idReziser", r.getIdReziser());
		
		
		return "IzaberiReziseraJasper";

	}
	
	
	
	@RequestMapping(value="/getFilmoviPoZanrovima", method=RequestMethod.POST)//bilo get
	public void showReportFpZ(HttpServletResponse response, HttpServletRequest request) throws Exception{
//		System.out.println("Usli smo showReportFpZ");
		Integer idReziser = (Integer) request.getSession().getAttribute("idReziser");
		
		List<Film> sviFilmovi = fr.findAll();
		
		sviFilmovi = sviFilmovi.stream()
				.filter(o -> o.getReziser().getIdReziser() == idReziser)
				.sorted(Comparator.comparing(Film::getGlavniZanrId))
				.toList();
		
//		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(fr.filmoviReziseraSortPoZanr(idReziser));
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(sviFilmovi);
		
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/FilmoviPoZanrovima.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		
		String imeRez = (String) request.getSession().getAttribute("imeRezisera");
		System.out.println("ime rezisera je: " + imeRez);
		params.put("ImeRezisera", imeRez);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=SviFilmoviOdRezisera.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
	}
	


}
