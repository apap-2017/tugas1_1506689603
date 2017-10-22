package com.haryop.tugas1.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel {
	private int id;
	private String nomorKk;
	private String alamat;
	private String rt;
	private String rw;
	private String idKelurahan;
	private int isTidakBerlaku;
	private String kelurahan;
	private String kecamatan;
	private String kota;
	private List<PendudukModel> anggotaKeluarga;
	
	public String generateNkkPart(String nkkPart) {
		String datePattern = "dd-MM-yyyy";
		String tanggalKk = new SimpleDateFormat(datePattern).format(new Date());

		String[] tanggalKkParts = tanggalKk.split("-");

		for (int i = 0; i < tanggalKkParts.length; i++) {
			if (i == tanggalKkParts.length - 1) {
				nkkPart = nkkPart + tanggalKkParts[i].substring(2, 4);
			} else {
				nkkPart = nkkPart + tanggalKkParts[i];
			}

		}
		return nkkPart;
	}
	
	public String generateNkk(String nkkPart, List<String> nkkIndexList) {
		if(nomorKk != null) {
			if(!nkkPart.equalsIgnoreCase(nomorKk.substring(0, 12))) {
				String nkkIndex = "";

				if (nkkIndexList.size() == 0) {
					nkkIndex = "" + 1;
				} else {
					nkkIndex = "" + (Integer.parseInt(nkkIndexList.get(nkkIndexList.size() - 1).substring(12)) + 1);
				}

				if (nkkIndex.length() == 1) {
					nkkIndex = "000" + nkkIndex;
				} else if (nkkIndex.length() == 2) {
					nkkIndex = "00" + nkkIndex;
				} else if (nkkIndex.length() == 3) {
					nkkIndex = "0" + nkkIndex;
				}

				String nkk = nkkPart + nkkIndex;

				setNomorKk(nkk);
				
				return nkk;
			} else {
				return nomorKk;
			}
		} else {
			String nkkIndex = "";

			if (nkkIndexList.size() == 0) {
				nkkIndex = "" + 1;
			} else {
				nkkIndex = "" + (Integer.parseInt(nkkIndexList.get(nkkIndexList.size() - 1).substring(12)) + 1);
			}

			if (nkkIndex.length() == 1) {
				nkkIndex = "000" + nkkIndex;
			} else if (nkkIndex.length() == 2) {
				nkkIndex = "00" + nkkIndex;
			} else if (nkkIndex.length() == 3) {
				nkkIndex = "0" + nkkIndex;
			}

			String nkk = nkkPart + nkkIndex;

			setNomorKk(nkk);
			
			return nkk;
		}
		
	}
	
	public boolean isStillValid() {
		for(PendudukModel penduduk : anggotaKeluarga) {
			if (penduduk.getIsWafat() == 0) {
				return false;
			}
		}
		return true;
	}
}
