package com.haryop.tugas1.dao;

import org.apache.ibatis.annotations.Param;

import com.haryop.tugas1.model.KotaModel;
import com.haryop.tugas1.model.KecamatanModel;
import com.haryop.tugas1.model.KelurahanModel;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DaerahMapper {
	@Select("select * from kota where id=#{id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="kodeKota", column="kode_kota"),
			@Result(property="namaKota", column="nama_kota")
	})
	public KotaModel selectKota(@Param(value="id")int id);
	
	@Select("select * from kota")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="kodeKota", column="kode_kota"),
			@Result(property="namaKota", column="nama_kota")
	})
	public List<KotaModel> selectAllKota();
	
	@Select("select * from kecamatan where id_kota = #{idKota}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="idKota", column="id_kota"),
			@Result(property="kodeKecamatan", column="kode_kecamatan"),
			@Result(property="namaKecamatan", column="nama_kecamatan")
	})
	public List<KecamatanModel> selectKecamatan(@Param(value = "idKota") int idKota);
	
	@Select("select * from kecamatan where id = #{id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="idKota", column="id_kota"),
			@Result(property="kodeKecamatan", column="kode_kecamatan"),
			@Result(property="namaKecamatan", column="nama_kecamatan")
	})
	public KecamatanModel selectKecamatanId(@Param(value = "id") int id);
	
	@Select("select * from kelurahan where id_kecamatan = #{idKecamatan}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="idKecamatan", column="id_kecamatan"),
			@Result(property="kodeKelurahan", column="kode_kelurahan"),
			@Result(property="namaKelurahan", column="nama_kelurahan"),
			@Result(property="kodePos", column="kode_pos"),
	})
	public List<KelurahanModel> selectKelurahan(@Param(value="idKecamatan") int idKecamatan);
	
	@Select("select * from kecamatan where id = #{id}")
	@Results(value = {
			@Result(property="id", column="id"),
			@Result(property="idKecamatan", column="id_kecamatan"),
			@Result(property="kodeKelurahan", column="kode_kelurahan"),
			@Result(property="namaKelurahan", column="nama_kelurahan"),
			@Result(property="kodePos", column="kode_pos"),
	})
	public KelurahanModel selectKelurahanId(@Param(value="id") int id);
}
