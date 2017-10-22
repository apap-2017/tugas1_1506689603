package com.haryop.tugas1.dao;

import org.apache.ibatis.annotations.Param;

import com.haryop.tugas1.model.KeluargaModel;
import com.haryop.tugas1.model.PendudukModel;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface KeluargaMapper {

	@Select("select * from keluarga k, kelurahan kel, kecamatan kec, kota kot "
			+ "where k.id = #{id} and kel.id = k.id_kelurahan and kec.id = kel.id_kecamatan "
			+ "and kot.id = kec.id_kota")
	@Results(value = { @Result(property = "id", column = "id"), @Result(property = "nomorKk", column = "nomor_kk"),
			@Result(property = "alamat", column = "alamat"), @Result(property = "rt", column = "rt"),
			@Result(property = "rw", column = "rw"), @Result(property = "idKelurahan", column = "id_kelurahan"),
			@Result(property = "isTidakBerlaku", column = "is_tidak_berlaku"),
			@Result(property = "kelurahan", column = "nama_kelurahan"),
			@Result(property = "kecamatan", column = "nama_kecamatan"),
			@Result(property = "kota", column = "nama_kota"),
			@Result(property = "anggotaKeluarga", column = "id", javaType = List.class, many = @Many(select = "selectAnggotaKeluarga")) })
	KeluargaModel selectKeluarga(@Param(value = "id") int id);

	@Select("select * from keluarga k, kelurahan kel, kecamatan kec, kota kot "
			+ "where k.nomor_kk = #{nomorKk} and kel.id = k.id_kelurahan and kec.id = kel.id_kecamatan "
			+ "and kot.id = kec.id_kota")
	@Results(value = { @Result(property = "id", column = "id"), @Result(property = "nomorKk", column = "nomor_kk"),
			@Result(property = "alamat", column = "alamat"), @Result(property = "rt", column = "rt"),
			@Result(property = "rw", column = "rw"), @Result(property = "idKelurahan", column = "id_kelurahan"),
			@Result(property = "isTidakBerlaku", column = "is_tidak_berlaku"),
			@Result(property = "kelurahan", column = "nama_kelurahan"),
			@Result(property = "kecamatan", column = "nama_kecamatan"),
			@Result(property = "kota", column = "nama_kota"),
			@Result(property = "anggotaKeluarga", column = "id", javaType = List.class, many = @Many(select = "selectAnggotaKeluarga")) })
	KeluargaModel selectKeluargaNkk(@Param(value = "nomorKk") String nomorKk);

	@Select("select s.nama, s.nik, s.jenis_kelamin, s.tempat_lahir, s.tanggal_lahir, s.agama, s.pekerjaan, s.status_perkawinan, "
			+ "s.status_dalam_keluarga, s.is_wni from penduduk s join keluarga k on s.id_keluarga = k.id "
			+ "where k.id = #{id}")
	@Results(value = { @Result(property = "nik", column = "nik"), @Result(property = "nama", column = "nama"),
			@Result(property = "tempatLahir", column = "tempat_lahir"),
			@Result(property = "tanggalLahir", column = "tanggal_lahir"),
			@Result(property = "jenisKelamin", column = "jenis_kelamin"),
			@Result(property = "isWni", column = "is_wni"), @Result(property = "agama", column = "agama"),
			@Result(property = "pekerjaan", column = "pekerjaan"),
			@Result(property = "statusPerkawinan", column = "status_perkawinan"),
			@Result(property = "statusDalamKeluarga", column = "status_dalam_keluarga"),
			@Result(property = "golonganDarah", column = "golongan_darah"),
			@Result(property = "isWafat", column = "is_wafat") })
	List<PendudukModel> selectAnggotaKeluarga(@Param(value = "id") int id);

	@Select("select kode_kecamatan from kelurahan kel, kecamatan kec where "
			+ "kel.id = #{idKelurahan} and kec.id = kel.id_kecamatan")
	String getKodeKecamatan(@Param(value = "idKelurahan") String idKelurahan);

	@Select("select nomor_kk from keluarga where nomor_kk like #{nkkPart}")
	List<String> generateNkkIndex(@Param("nkkPart") String nikkPart);

	@Insert("INSERT INTO keluarga (nomor_kk, alamat, rt, rw, id_kelurahan) "
			+ "VALUES (#{nomorKk}, #{alamat}, #{rt}, #{rw}, #{idKelurahan})")
	void addKeluarga(KeluargaModel keluarga);

	@Update("UPDATE keluarga SET nomor_kk=#{nomorKk},alamat=#{alamat},RT=#{rt},"
			+ "RW=#{rw},id_kelurahan=#{idKelurahan} WHERE id=#{id}")
	void updateKeluarga(KeluargaModel keluarga);

	@Update("UPDATE keluarga SET is_tidak_berlaku = 1 WHERE id=#{id}")
	void disableKeluarga(KeluargaModel keluarga);

}