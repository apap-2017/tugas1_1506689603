package com.haryop.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haryop.tugas1.dao.DaerahMapper;
import com.haryop.tugas1.model.KecamatanModel;
import com.haryop.tugas1.model.KelurahanModel;
import com.haryop.tugas1.model.KotaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DaerahServiceDatabase implements DaerahService{
	
	@Autowired
	DaerahMapper daerahMapper;

	@Override
	public List<KotaModel> selectAllKota() {
		return daerahMapper.selectAllKota();
	}

	@Override
	public List<KecamatanModel> selectKecamatan(int idKota) {
		// TODO Auto-generated method stub
		return daerahMapper.selectKecamatan(idKota);
	}

	@Override
	public List<KelurahanModel> selectKelurahan(int idKecamatan) {
		// TODO Auto-generated method stub
		return daerahMapper.selectKelurahan(idKecamatan);
	}

	@Override
	public KecamatanModel selectKecamatanId(int id) {
		// TODO Auto-generated method stub
		return daerahMapper.selectKecamatanId(id);
	}

	@Override
	public KelurahanModel selectKelurahanId(int id) {
		// TODO Auto-generated method stub
		return daerahMapper.selectKelurahanId(id);
	}

	@Override
	public KotaModel selectKota(int id) {
		// TODO Auto-generated method stub
		return daerahMapper.selectKota(id);
	}

}
