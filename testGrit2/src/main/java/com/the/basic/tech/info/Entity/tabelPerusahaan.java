package com.the.basic.tech.info.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Setter
@Getter
@Entity
@Table (name = "tabel_perusahaan")
public class tabelPerusahaan implements Serializable {
    @Id
    @Column(name = "id_perusahaan", nullable = false)
    private String idPerusahaan;
    @Column(name = "kode_perusahaan", nullable = true)
    private String kodePerusahaan;
    @Column(name = "nama_perusahaan", nullable = true)
    private String namaPerusahaan;
    @Column(name = "user_perusahaan", nullable = true)
    private String userPerusahaan;

}
