package com.example.demo.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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
import com.example.demo.repository.ReziserRepo;
import com.example.demo.repository.ZanrRepo;

import model.Film;
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
		m.addAttribute("poruka", "Reziser je uspesno sacuvan.");
		System.out.println("Film: "+ novi +" je sacuvan");
		
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
	
	@RequestMapping(value="/faveFilm", method=RequestMethod.GET)
	public String faveFilm(Integer idF, Integer idK, HttpServletRequest request) {
		Film f =  fr.findById(idF).get();
		
		
		return "";
	}
	
	@RequestMapping(value="/filmUPerodu", method=RequestMethod.GET)
	public String filmUPerodu(Date datumOd, Date datumDo,  HttpServletRequest request) {
		
		List<Film> filmovi = fr.filmoviUPerioduODDO(datumOd, datumDo);
		request.getSession().setAttribute("filmovi", filmovi);
		
		
		return "FilmoviUPeriodu";
	}
	
	@RequestMapping(value="/sortiraniFilmovi", method=RequestMethod.GET)
	public String sortiraniFilmovi(HttpServletRequest request) {
		
		List<Film> filmovi = fr.sortiraniFilmoviR();
		request.getSession().setAttribute("filmovi", filmovi);
		
		return "sortiraniFilmovi";
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

	@RequestMapping(value="/faveFilm", method=RequestMethod.POST)
	public String saveFilm(Integer idF,  Model m, HttpServletRequest request  ) {
		
		
		
		
		return ""; //ISTU STRANICU
	}
		
	


}
