package com.haryop.tugas1.service;

import java.util.List;

import com.haryop.tugas1.model.PendudukModel;

public interface PendudukService {
	
	PendudukModel selectPenduduk(String nik);
	
	List<String> generateNikIndex(String nik);
	
	void addPenduduk(PendudukModel penduduk);
	
	void updatePenduduk (PendudukModel penduduk);
	
	void killPenduduk (PendudukModel penduduk);
	
	List<PendudukModel> selectPendudukIn(int idKelurahan);
	
	PendudukModel getOldestPenduduk(List<PendudukModel> pendudukList);
	
	PendudukModel getYoungestPenduduk(List<PendudukModel> pendudukList);
}
