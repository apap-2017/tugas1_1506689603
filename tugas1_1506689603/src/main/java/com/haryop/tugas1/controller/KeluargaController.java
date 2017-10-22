package com.haryop.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haryop.tugas1.model.KeluargaModel;
import com.haryop.tugas1.model.PendudukModel;
import com.haryop.tugas1.service.KeluargaService;
import com.haryop.tugas1.service.PendudukService;

@Controller
public class KeluargaController {

	@Autowired
	KeluargaService keluargaDAO;

	@Autowired
	PendudukService pendudukDAO;

	@RequestMapping(value = "/keluarga", method = RequestMethod.GET)
	public String viewKeluarga(Model model, @RequestParam(value = "nkk", required = true) String nkk) {

		KeluargaModel keluarga = keluargaDAO.selectKeluargaNkk(nkk);

		if (keluarga != null) {
			model.addAttribute("keluarga", keluarga);
			return "view-keluarga";
		} else {
			model.addAttribute("nkk", nkk);
			model.addAttribute("status", "keluarga");
			model.addAttribute("title", "Keluarga Not Found");
			return "not-found";
		}
	}

	@RequestMapping(value = "/keluarga/tambah")
	public String addKeluarga(@ModelAttribute("keluarga") KeluargaModel keluarga) {
		return "form-add-keluarga";
	}

	@RequestMapping(value = "/keluarga/tambah", method = RequestMethod.POST)
	public String addKeluarga(Model model, @ModelAttribute KeluargaModel keluarga, BindingResult result) {

		String nkkPart = "";
		String kodeKecamatan = keluargaDAO.getKodeKecamatan(keluarga.getIdKelurahan());
		kodeKecamatan = kodeKecamatan.substring(0, kodeKecamatan.length() - 1);
		nkkPart = nkkPart + kodeKecamatan;
		nkkPart = keluarga.generateNkkPart(nkkPart);
		List<String> nkkIndexList = keluargaDAO.generateNkkIndex("%" + nkkPart + "%");
		String nkk = keluarga.generateNkk(nkkPart, nkkIndexList);

		model.addAttribute("nkk", nkk);
		keluargaDAO.addKeluarga(keluarga);

		return "add-keluarga-success";
	}

	@RequestMapping(value = "/keluarga/ubah/{nkk}")
	public String updateKeluarga(Model model, @PathVariable(value = "nkk") String nkk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluargaNkk(nkk);
		if (keluarga != null) {
			model.addAttribute("keluarga", keluarga);
			return "form-update-keluarga";
		} else {
			model.addAttribute("nkk", nkk);
			model.addAttribute("status", "keluarga");
			model.addAttribute("title", "Keluarga Not Found");
			return "not-found";
		}
	}

	@RequestMapping(value = "/keluarga/ubah/{nkk}", method = RequestMethod.POST)
	public String updateKeluargaSubmit(Model model, @ModelAttribute KeluargaModel keluarga, BindingResult result) {

		String nkkPart = "";
		String kodeKecamatan = keluargaDAO.getKodeKecamatan(keluarga.getIdKelurahan());

		String nkkLama = keluarga.getNomorKk();

		kodeKecamatan = kodeKecamatan.substring(0, kodeKecamatan.length() - 1);
		nkkPart = nkkPart + kodeKecamatan;
		nkkPart = keluarga.generateNkkPart(nkkPart);
		List<String> nkkIndexList = keluargaDAO.generateNkkIndex("%" + nkkPart + "%");
		String nkk = keluarga.generateNkk(nkkPart, nkkIndexList);

		model.addAttribute("nkk", nkkLama);
		model.addAttribute("nkkbaru", nkk);

		keluargaDAO.updateKeluarga(keluarga);

		KeluargaModel keluargaDatabase = keluargaDAO.selectKeluargaNkk(keluarga.getNomorKk());

		for (PendudukModel penduduk : keluargaDatabase.getAnggotaKeluarga()) {

			String nikPart = penduduk.generateNikPart(kodeKecamatan);
			List<String> nikIndexList = pendudukDAO.generateNikIndex("%" + nikPart + "%");
			String nik = penduduk.generateNik(nikIndexList, nikPart);
			pendudukDAO.updatePenduduk(penduduk);

		}

		return "update-keluarga-success";
	}

}
