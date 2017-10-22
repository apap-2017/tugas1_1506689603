package com.haryop.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.haryop.tugas1.model.PendudukModel;

import org.apache.ibatis.annotations.Many;

@Mapper
public interface PendudukMapper {
	
	@Select("select * from penduduk where nik = #{nik}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="nik", column="nik"),
			@Result(property="nama", column="nama"),
			@Result(property="tempatLahir", column="tempat_lahir"),
			@Result(property="tanggalLahir", column="tanggal_lahir"),
			@Result(property="jenisKelamin", column="jenis_kelamin"),
			@Result(property="isWni", column="is_wni"),
			@Result(property="idKeluarga", column="id_keluarga"),
			@Result(property="agama", column="agama"),
			@Result(property="pekerjaan", column="pekerjaan"),
			@Result(property="statusPerkawinan", column="status_perkawinan"),
			@Result(property="statusDalamKeluarga", column="status_dalam_keluarga"),
			@Result(property="golonganDarah", column="golongan_darah"),
			@Result(property="isWafat", column="is_wafat")
	})
	PendudukModel selectPenduduk(@Param("nik") String nik); 
	
	@Select("select * from penduduk p where p.id_keluarga in "
			+ "( select k.id from keluarga k where k.id_kelurahan = #{idKelurahan} )")
	@Results(value={
			@Result(property="id", column="id"),
			@Result(property="nik", column="nik"),
			@Result(property="nama", column="nama"),
			@Result(property="tempatLahir", column="tempat_lahir"),
			@Result(property="tanggalLahir", column="tanggal_lahir"),
			@Result(property="jenisKelamin", column="jenis_kelamin"),
			@Result(property="isWni", column="is_wni"),
			@Result(property="idKeluarga", column="id_keluarga"),
			@Result(property="agama", column="agama"),
			@Result(property="pekerjaan", column="pekerjaan"),
			@Result(property="statusPerkawinan", column="status_perkawinan"),
			@Result(property="statusDalamKeluarga", column="status_dalam_keluarga"),
			@Result(property="golonganDarah", column="golongan_darah"),
			@Result(property="isWafat", column="is_wafat")
	})
	List<PendudukModel> selectPendudukIn(@Param("idKelurahan") int idKelurahan);
	
	@Select("select nik from penduduk where nik like #{nikPart}")
	List<String> generateNikIndex(@Param("nikPart") String nikPart);
	
	@Insert("INSERT INTO penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, "
			+ "agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat) "
			+ "VALUES (#{nik}, #{nama}, #{tempatLahir}, #{tanggalLahir}, #{jenisKelamin}, #{isWni}, #{idKeluarga}, "
			+ "#{agama}, #{pekerjaan}, #{statusPerkawinan}, #{statusDalamKeluarga}, #{golonganDarah}, #{isWafat})")
    void addPenduduk (PendudukModel penduduk);
	
	@Update("UPDATE penduduk SET nik=#{nik},nama=#{nama},tempat_lahir=#{tempatLahir},tanggal_lahir=#{tanggalLahir},"
			+ "jenis_kelamin=#{jenisKelamin},is_wni=#{isWni},id_keluarga=#{idKeluarga},agama=#{agama},pekerjaan=#{pekerjaan},"
			+ "status_perkawinan=#{statusPerkawinan},status_dalam_keluarga=#{statusDalamKeluarga},golongan_darah=#{golonganDarah},"
			+ "is_wafat=#{isWafat} WHERE id=#{id}")
    void updatePenduduk (PendudukModel penduduk);
	
	@Update("UPDATE penduduk SET is_wafat=1 WHERE id=#{id}")
    void killPenduduk (PendudukModel penduduk);

}
