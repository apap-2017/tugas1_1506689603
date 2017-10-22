package com.haryop.tugas1.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.haryop.tugas1.model.KeluargaModel;
import com.haryop.tugas1.model.KotaModel;
import com.haryop.tugas1.model.KecamatanModel;
import com.haryop.tugas1.model.KelurahanModel;
import com.haryop.tugas1.model.PendudukModel;
import com.haryop.tugas1.service.DaerahService;
import com.haryop.tugas1.service.KeluargaService;
import com.haryop.tugas1.service.PendudukService;

@Controller
public class PendudukController {

	@Autowired
	PendudukService pendudukDAO;

	@Autowired
	KeluargaService keluargaDAO;

	@Autowired
	DaerahService daerahDAO;

	@RequestMapping(value = "/penduduk", method = RequestMethod.GET)
	public String viewPenduduk(Model model, @RequestParam(value = "nik", required = true) String nik,
			HttpServletRequest request) {

		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		if (penduduk != null) {
			KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getIdKeluarga());
			if (keluarga != null) {
				model.addAttribute("penduduk", penduduk);
				model.addAttribute("keluarga", keluarga);
				model.addAttribute("button", "button");
				java.util.Enumeration<String> reqEnum = request.getParameterNames();
				while (reqEnum.hasMoreElements()) {
					String s = reqEnum.nextElement();
					System.out.println(s);
					System.out.println("==" + request.getParameter(s));
					if (request.getParameter(s).equals("true")) {
						model.addAttribute("redirect", "true");
					}
				}
				return "view-penduduk";
			} else {
				model.addAttribute("id", penduduk.getIdKeluarga());
				model.addAttribute("status", "penduduk");
				model.addAttribute("title", "Penduduk Not Found");
				return "not-found";
			}
		} else {
			model.addAttribute("nik", nik);
			model.addAttribute("status", "penduduk");
			model.addAttribute("title", "Penduduk Not Found");
			return "not-found";
		}
	}

	@RequestMapping(value = "/penduduk/{nik}")
	public String showPenduduk(Model model, @PathVariable(value = "nik") String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		if (penduduk != null) {
			KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getIdKeluarga());
			if (keluarga != null) {
				model.addAttribute("penduduk", penduduk);
				model.addAttribute("keluarga", keluarga);
				return "view-penduduk";
			} else {
				model.addAttribute("id", penduduk.getIdKeluarga());
				model.addAttribute("status", "penduduk");
				model.addAttribute("title", "Penduduk Not Found");
				return "not-found";
			}
		} else {
			model.addAttribute("nik", nik);
			model.addAttribute("status", "penduduk");
			model.addAttribute("title", "Penduduk Not Found");
			return "not-found";
		}
	}

	@RequestMapping("/penduduk/tambah")
	public String addPendudukSubmit(@ModelAttribute("penduduk") PendudukModel penduduk) {
		return "form-add-penduduk";
	}

	@RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
	public String addPenduduk(Model model, @ModelAttribute PendudukModel penduduk, BindingResult result) throws ParseException {
		System.out.println(penduduk.getTanggalLahir() + "hehe");
		
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getIdKeluarga());
		String kodeKecamatan = keluargaDAO.getKodeKecamatan(keluarga.getIdKelurahan());
		kodeKecamatan = kodeKecamatan.substring(0, kodeKecamatan.length() - 1);
		String nikPart = penduduk.generateNikPart(kodeKecamatan);
		List<String> nikIndexList = pendudukDAO.generateNikIndex("%" + nikPart + "%");
		String nik = penduduk.generateNik(nikIndexList, nikPart);
		
		/*penduduk.setTanggalLahir("20" + penduduk.getTanggalLahir().substring(0, 2) + "-"
				+ penduduk.getTanggalLahir().substring(2, 4) + "-" + penduduk.getTanggalLahir().substring(4));
		
		penduduk.formatTanggalLahir();
		
		System.out.println(penduduk.getTanggalLahir() + "--" + penduduk.getTanggalLahir());*/
		
		System.out.println(penduduk.getTanggalLahir());
		
		model.addAttribute("penduduk", penduduk);
		pendudukDAO.addPenduduk(penduduk);
		return "add-penduduk-success";
	}

	@RequestMapping(value = "/penduduk/ubah/{nik}")
	public String updatePenduduk(Model model, @PathVariable(value = "nik") String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		if (penduduk != null) {
			model.addAttribute("penduduk", penduduk);
			return "form-update-penduduk";
		} else {
			model.addAttribute("nik", nik);
			model.addAttribute("status", "penduduk");
			model.addAttribute("title", "Penduduk Not Found");
			return "not-found";
		}
	}

	@RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
	public String updatePendudukSubmit(Model model, @ModelAttribute PendudukModel penduduk, BindingResult result)
			throws ParseException {

		KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getIdKeluarga());
		String kodeKecamatan = keluargaDAO.getKodeKecamatan(keluarga.getIdKelurahan());

		String nikLama = penduduk.getNik();
		kodeKecamatan = kodeKecamatan.substring(0, kodeKecamatan.length() - 1);
		String nikPart = penduduk.generateNikPart(kodeKecamatan);
		List<String> nikIndexList = pendudukDAO.generateNikIndex("%" + nikPart + "%");
		String nik = penduduk.generateNik(nikIndexList, nikPart);

		pendudukDAO.updatePenduduk(penduduk);
		model.addAttribute("nik", nikLama);
		model.addAttribute("nikbaru", nik);
		return "update-penduduk-success";
	}

	@RequestMapping(value = "/penduduk/mati", method = RequestMethod.POST)
	public String setPendudukWafat(Model model, @ModelAttribute PendudukModel penduduk, BindingResult result,
			RedirectAttributes redirectAttributes) {
		penduduk.setIsWafat(1);

		PendudukModel pendudukDatabase = pendudukDAO.selectPenduduk(penduduk.getNik());

		pendudukDAO.killPenduduk(pendudukDatabase);

		KeluargaModel keluarga = keluargaDAO.selectKeluarga(pendudukDatabase.getIdKeluarga());

		if (!keluarga.isStillValid()) {
			keluargaDAO.disableKeluarga(keluarga);
		}

		redirectAttributes.addAttribute("redirect", "true");

		return "redirect:/penduduk?nik=" + pendudukDatabase.getNik();
	}

	@RequestMapping(value = "/penduduk/cari")
	public String searchPenduduk(Model model, @RequestParam(value = "kt", required = false) Integer idKota,
			@RequestParam(value = "kc", required = false) Integer idKecamatan,
			@RequestParam(value = "kl", required = false) Integer idKelurahan) {

		if (idKota == null && idKecamatan == null && idKelurahan == null) {

			List<KotaModel> kota = daerahDAO.selectAllKota();

			model.addAttribute("formInput", "kota");
			model.addAttribute("listKota", kota);

			return "search-penduduk";
		} else if (idKecamatan == null && idKelurahan == null) {

			List<KecamatanModel> kecamatan = daerahDAO.selectKecamatan(idKota);
			KotaModel kota = daerahDAO.selectKota(idKota);

			model.addAttribute("formInput", "kecamatan");
			model.addAttribute("kota", kota);
			model.addAttribute("listKecamatan", kecamatan);

			return "search-penduduk";
		} else if (idKelurahan == null) {

			List<KelurahanModel> kelurahan = daerahDAO.selectKelurahan(idKecamatan);
			KecamatanModel kecamatan = daerahDAO.selectKecamatanId(idKecamatan);
			KotaModel kota = daerahDAO.selectKota(kecamatan.getIdKota());

			model.addAttribute("formInput", "kelurahan");
			model.addAttribute("kota", kota);
			model.addAttribute("kecamatan", kecamatan);
			model.addAttribute("listKelurahan", kelurahan);

			return "search-penduduk";
		} else {

			List<PendudukModel> pendudukList = pendudukDAO.selectPendudukIn(idKelurahan);

			PendudukModel oldest = pendudukDAO.getOldestPenduduk(pendudukList);
			PendudukModel youngest = pendudukDAO.getYoungestPenduduk(pendudukList);
			
			System.out.println(oldest);

			model.addAttribute("formInput", "show");
			model.addAttribute("listPenduduk", pendudukList);
			model.addAttribute("pendudukTertua", oldest);
			model.addAttribute("pendudukTermuda", youngest);
			return "search-penduduk";
		}
	}

}
