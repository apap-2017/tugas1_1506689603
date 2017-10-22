package com.haryop.tugas1.service;

import java.util.List;

import com.haryop.tugas1.model.KotaModel;
import com.haryop.tugas1.model.KecamatanModel;
import com.haryop.tugas1.model.KelurahanModel;

public interface DaerahService {
	public List<KotaModel> selectAllKota();
	public KotaModel selectKota(int id);
	public List<KecamatanModel> selectKecamatan(int idKota);
	public KecamatanModel selectKecamatanId(int id);
	public List<KelurahanModel> selectKelurahan(int idKecamatan);
	public KelurahanModel selectKelurahanId(int id);
}
