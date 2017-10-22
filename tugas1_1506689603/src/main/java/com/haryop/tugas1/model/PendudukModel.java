package com.haryop.tugas1.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel implements Comparable<PendudukModel>{
	private int id;
	private String nik;
	private String nama;
	private String tempatLahir;
	private String tanggalLahir;
	private int jenisKelamin;
	private int isWni;
	private int idKeluarga;
	private String agama;
	private String pekerjaan;
	private String statusPerkawinan;
	private String statusDalamKeluarga;
	private String golonganDarah;
	private int isWafat;
	private Date tanggalLahirDatabase;

	public String generateNikPart(String kodeKecamatan) {
		String[] tanggalLahirParts = tanggalLahir.split("-");

		String tanggalLahirCal = "";
		for (int i = 2; i >= 0; i--) {
			if (i == 0) {
				tanggalLahirCal = tanggalLahirCal + tanggalLahirParts[i].substring(2, 4);
			} else {
				tanggalLahirCal = tanggalLahirCal + tanggalLahirParts[i];
			}
		}

		if (jenisKelamin == 1) {
			int tanggalLahirInt = Integer.parseInt(tanggalLahirCal);
			tanggalLahirCal = "" + (tanggalLahirInt + 40);
		}

		String nikPart = kodeKecamatan + tanggalLahirCal;

		return nikPart;
	}
	
	public String generateNik(List<String> nikIndexList, String nikPart) {
		if(!nikPart.equalsIgnoreCase(nik.substring(0, 12))) {
			String nikIndex = "";
			if(nikIndexList.size() == 0) {
				nikIndex = "" + 1;
			} else {
				nikIndex = "" + (Integer.parseInt(nikIndexList.get(nikIndexList.size() - 1).substring(12)) + 1);
			}
			
			if(nikIndex.length() == 1) {
				nikIndex = "000" + nikIndex;
			} else if(nikIndex.length() == 2) {
				nikIndex = "00" + nikIndex;
			} else if(nikIndex.length() == 3) {
				nikIndex = "0" + nikIndex;
			}
			
			String nik = nikPart + nikIndex;		
			setNik(nik);
			return nik;
		} else {
			return nik;
		}
		
	}
	
	public void formatTanggalLahir() throws ParseException {
		String tl = "";
		for (int i = 0; i < tanggalLahir.length(); i++) {
			if (i == 1 || i == 3) {
				tl = tl + tanggalLahir.charAt(i) + "-";
			} else {
				tl = tl + tanggalLahir.charAt(i);
			}
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateStr = formatter.parse(tanggalLahir);
		tanggalLahirDatabase = new java.sql.Date(dateStr.getTime());
	}

	@Override
	public int compareTo(PendudukModel o) {
		try {
			this.formatTanggalLahir();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			o.formatTanggalLahir();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.tanggalLahirDatabase.compareTo(o.tanggalLahirDatabase);
	}
}
