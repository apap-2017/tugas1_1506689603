package com.haryop.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haryop.tugas1.dao.KeluargaMapper;
import com.haryop.tugas1.model.KeluargaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService{
	
	@Autowired
	KeluargaMapper keluargaMapper;
	
	@Override
	public KeluargaModel selectKeluarga(int id) {
		KeluargaModel keluarga = keluargaMapper.selectKeluarga(id);
		log.info("-----" + keluarga.getKelurahan() + "------");
		return keluarga;
	}

	@Override
	public KeluargaModel selectKeluargaNkk(String nomorKk) {
		KeluargaModel keluarga = keluargaMapper.selectKeluargaNkk(nomorKk);
		//log.info("-----" + keluarga.getAnggotaKeluarga().get(0) + "------");
		return keluarga;
	}

	@Override
	public String getKodeKecamatan(String idKelurahan) {
		return keluargaMapper.getKodeKecamatan(idKelurahan);
	}

	@Override
	public List<String> generateNkkIndex(String nkkPart) {
		return keluargaMapper.generateNkkIndex(nkkPart);
	}

	@Override
	public void addKeluarga(KeluargaModel keluarga) {
		keluargaMapper.addKeluarga(keluarga);
	}

	@Override
	public void updateKeluarga(KeluargaModel keluarga) {
		keluargaMapper.updateKeluarga(keluarga);
	}

	@Override
	public void disableKeluarga(KeluargaModel keluarga) {
		// TODO Auto-generated method stub
		keluargaMapper.disableKeluarga(keluarga);
	}

}
