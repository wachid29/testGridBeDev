package com.the.basic.tech.info.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Setter
@Getter
@Entity
@Table (name = "tabel_transaksi")
public class tabelTransaksi implements Serializable {
    @Id
    @Column(name = "id_transaksi", nullable = false)
    private String idTransaksi;
    @Column(name = "tanggal_transaksi", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date tanggalTransaksi;
    @Column(name = "nama_perusahaan", nullable = true)
    private String namaPerusahaan;
    @Column(name = "nama_barang", nullable = true)
    private String namaBarang;
    @Column(name = "total_barang", nullable = true)
    private Integer totalBarang;
    @Column(name = "harga_barang", nullable = true)
    private Integer hargaBarang;
    @Column(name = "grand_total", nullable = true)
    private Integer grandTotal;
    @Column(name = "sisa_barang", nullable = true)
    private Integer sisaBarang;

}
