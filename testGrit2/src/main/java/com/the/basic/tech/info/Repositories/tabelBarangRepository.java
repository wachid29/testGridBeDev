package com.the.basic.tech.info.Repositories;

import com.the.basic.tech.info.Entity.tabelBarang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface tabelBarangRepository extends JpaRepository<tabelBarang,String> {
    @Query(value = "SELECT COUNT(*) AS total FROM tabel_barang WHERE LOWER(nama_barang)=LOWER(:namaBarang)" , nativeQuery = true)
    Integer getExistBarang(String namaBarang);

    @Query(value = "SELECT * FROM tabel_barang WHERE LOWER(nama_barang)=LOWER(:namaBarang) LIMIT 1" , nativeQuery = true)
    tabelBarang getBarangByName(String namaBarang);

}
