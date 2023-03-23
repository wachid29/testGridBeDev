package com.the.basic.tech.info.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Setter
@Getter
@Entity
@Table (name = "tabel_barang")
public class tabelBarang implements Serializable {
    @Id
    @Column(name = "id_barang", nullable = false)
    private String idBarang;
    @Column(name = "nama_barang", nullable = true)
    private String namaBarang;
    @Column(name = "harga", nullable = true)
    private Integer harga;
    @Column(name = "stock", nullable = true)
    private Integer stock;

}
