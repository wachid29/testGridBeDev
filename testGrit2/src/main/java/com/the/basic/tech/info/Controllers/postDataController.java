package com.the.basic.tech.info.Controllers;


import com.the.basic.tech.info.Entity.*;
import com.the.basic.tech.info.Services.postDataService;
import com.the.basic.tech.info.Utility.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class postDataController {

    @Autowired
    private postDataService postDataService;

    @PostMapping("/addPerusahaan")
    private ResponseEntity<MessageModel> addPerusahaan(@RequestBody tabelPerusahaan tabelPerusahaan,@RequestHeader (name="Authorization") String token) {
        ResponseEntity responseEntity = postDataService.addDataPerusahaan(tabelPerusahaan,token);
        return responseEntity;
    }

    @PostMapping("/addTransaksi")
    private ResponseEntity<MessageModel> addTransaksi(@RequestBody tabelTransaksi tabelTransaksi,@RequestHeader (name="Authorization") String token) {
        ResponseEntity responseEntity = postDataService.addDataTransaksi(tabelTransaksi,token);
        return responseEntity;
    }

    @GetMapping("/transaction/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=transaction_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<tabelTransaksi> listTransaction = postDataService.listAllTransaction();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = { "Tanggal Transaksi","Nama Perusahaan", "Nama Barang", "Total Barang", "Harga Barang","Grand Total","SisaBarang"};
        String[] nameMapping = { "tanggalTransaksi","namaPerusahaan", "namaBarang", "totalBarang", "hargaBarang","grandTotal","sisaBarang"};

        csvWriter.writeHeader(csvHeader);

        for (tabelTransaksi transaksi : listTransaction) {
            csvWriter.write(transaksi, nameMapping);
        }

        csvWriter.close();

    }

}

