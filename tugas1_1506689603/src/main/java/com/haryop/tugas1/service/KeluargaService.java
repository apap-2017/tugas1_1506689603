package com.haryop.tugas1.service;

import java.util.List;

import com.haryop.tugas1.model.KeluargaModel;

public interface KeluargaService {
	
	KeluargaModel selectKeluarga(int id);
	
	KeluargaModel selectKeluargaNkk(String nomorKk);
	
	String getKodeKecamatan(String idKelurahan);
	
	List<String> generateNkkIndex(String nkkPart);
	
	void addKeluarga(KeluargaModel keluarga);
	
	void updateKeluarga (KeluargaModel keluarga);
	
	void disableKeluarga(KeluargaModel keluarga);
}
