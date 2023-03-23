package com.the.basic.tech.info.Repositories;

import com.the.basic.tech.info.Entity.tabelPerusahaan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface tabelPerusahaanRepository extends JpaRepository<tabelPerusahaan,String> {
    @Query(value = "SELECT COUNT(*) AS total FROM tabel_perusahaan WHERE LOWER(nama_perusahaan)=LOWER(:namaPerusahaan) OR LOWER(kode_perusahaan)=LOWER(:kodePerusahaan)" , nativeQuery = true)
    Integer getExistPerusahaan(String namaPerusahaan,String kodePerusahaan);
    @Query(value = "SELECT COUNT(*) AS total FROM tabel_perusahaan WHERE LOWER(nama_perusahaan)=LOWER(:namaPerusahaan)" , nativeQuery = true)
    Integer validasiNamaPerusahaan(String namaPerusahaan );
    @Query(value = "SELECT COUNT(*) AS total FROM tabel_perusahaan WHERE LOWER(nama_perusahaan)=LOWER(:namaPerusahaan) AND LOWER(user_perusahaan)=LOWER(:userPerusahaan)" , nativeQuery = true)
    Integer validasiNamaUserPerusahaan(String namaPerusahaan,String userPerusahaan);

}
