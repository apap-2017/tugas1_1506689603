package com.haryop.tugas1.service;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haryop.tugas1.dao.PendudukMapper;
import com.haryop.tugas1.model.PendudukModel;

@Service
public class PendudukServiceDatabase implements PendudukService {

	@Autowired
	PendudukMapper pendudukMapper;

	@Override
	public PendudukModel selectPenduduk(String nik) {
		return pendudukMapper.selectPenduduk(nik);
	}

	@Override
	public List<String> generateNikIndex(String nik) {
		return pendudukMapper.generateNikIndex(nik);
	}

	@Override
	public void addPenduduk(PendudukModel penduduk) {
		pendudukMapper.addPenduduk(penduduk);

	}

	@Override
	public void updatePenduduk(PendudukModel penduduk) {
		pendudukMapper.updatePenduduk(penduduk);
	}

	@Override
	public void killPenduduk(PendudukModel penduduk) {
		pendudukMapper.killPenduduk(penduduk);

	}

	@Override
	public List<PendudukModel> selectPendudukIn(int idKelurahan) {
		// TODO Auto-generated method stub
		return pendudukMapper.selectPendudukIn(idKelurahan);
	}

	@Override
	public PendudukModel getOldestPenduduk(List<PendudukModel> pendudukList) {
		ListIterator<PendudukModel> itr = pendudukList.listIterator();

		PendudukModel oldest = itr.next();
		while (itr.hasNext()) {

			PendudukModel current = itr.next();

			if (current.compareTo(oldest) < 0) {
				oldest = current;
			}
		}

		return oldest;
	}

	@Override
	public PendudukModel getYoungestPenduduk(List<PendudukModel> pendudukList) {
		ListIterator<PendudukModel> itr = pendudukList.listIterator();

		PendudukModel youngest = itr.next();
		while (itr.hasNext()) {
			final PendudukModel current = itr.next();
			if (current.compareTo(youngest) > 0) {
				youngest = current;
			}
		}

		return youngest;
	}

}
