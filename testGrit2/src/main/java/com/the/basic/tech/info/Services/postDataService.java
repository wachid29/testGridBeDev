package com.the.basic.tech.info.Services;

import com.the.basic.tech.info.Entity.*;
import com.the.basic.tech.info.Utility.MessageModel;
import com.the.basic.tech.info.Config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.the.basic.tech.info.Repositories.*;

import java.util.*;


@Service
public class postDataService {

    @Autowired
    private tabelUserRepository tabelUserRepository;
    @Autowired
    private tabelBarangRepository tabelBarangRepository;
    @Autowired
    private tabelTransaksiRepository tabelTransaksiRepository;
    @Autowired
    private tabelPerusahaanRepository tabelPerusahaanRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public ResponseEntity<MessageModel> signup(tabelUser payload)
    {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();

        try{
            Integer existUser = tabelUserRepository.getExistUser(payload.getUsername(), payload.getEmail());

            if(existUser>0){
                msg.setStatus(false);
                msg.setMessage("username or email already exist");
                return ResponseEntity.status(HttpStatus.OK).body(msg);
            }

            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String encrypted = bcrypt.encode(payload.getPassword());

            payload.setIdUser(String.valueOf(UUID.randomUUID()));
            payload.setPassword(encrypted);
            tabelUserRepository.save(payload);
            msg.setStatus(true);
            msg.setMessage("Success Signup");
            return ResponseEntity.status(HttpStatus.OK).body(msg);

        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    public ResponseEntity<MessageModel> login(tabelUser payload)
    {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();

        try{
            tabelUser validateUser = tabelUserRepository.validateUser(payload.getUsername());

            if(validateUser == null) {
                msg.setStatus(false);
                msg.setMessage("username not registered");
                return ResponseEntity.status(HttpStatus.OK).body(msg);
            }

            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            if(!bcrypt.matches(payload.getPassword(), validateUser.getPassword())){
                msg.setStatus(false);
                msg.setMessage("password incorrect");
                return ResponseEntity.status(HttpStatus.OK).body(msg);
            }

            String token = jwtTokenUtil.generateToken2(validateUser);

            msg.setStatus(true);
            msg.setMessage("success login");
            result.put("username", validateUser.getUsername());
            result.put("accessToken", token);
            msg.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(msg);

        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    public ResponseEntity<MessageModel> addDataBarang(tabelBarang payload)
    {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();

        try{

            Integer existBarang= tabelBarangRepository.getExistBarang(payload.getNamaBarang());
            if(existBarang>0){
                msg.setStatus(false);
                msg.setMessage("barang already exist");
                return ResponseEntity.status(HttpStatus.OK).body(msg);
            }

            payload.setIdBarang(String.valueOf(UUID.randomUUID()));
            tabelBarangRepository.save(payload);
            msg.setStatus(true);
            msg.setMessage("success");
            result.put("barang", payload);
            msg.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    public ResponseEntity<MessageModel> addDataPerusahaan(tabelPerusahaan payload,String token)
    {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();

        try{



            Integer existPerusahaan= tabelPerusahaanRepository.getExistPerusahaan(payload.getNamaPerusahaan(), payload.getKodePerusahaan());
            if(existPerusahaan>0){
                msg.setStatus(false);
                msg.setMessage("perusahaan already exist");
                return ResponseEntity.status(HttpStatus.OK).body(msg);
            }

            String newToken = token.substring(7);
            String userName = jwtTokenUtil.getUsernameFromToken(newToken);

            payload.setIdPerusahaan(String.valueOf(UUID.randomUUID()));
            payload.setUserPerusahaan(userName);
            tabelPerusahaanRepository.save(payload);
            msg.setStatus(true);
            msg.setMessage("success");
            result.put("perusahaan", payload);
            msg.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    public ResponseEntity<MessageModel> addDataTransaksi(tabelTransaksi payload,String token)
    {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();

        try{
            String newToken = token.substring(7);
            String userName = jwtTokenUtil.getUsernameFromToken(newToken);

            Integer namaPerusahaan= tabelPerusahaanRepository.validasiNamaPerusahaan(payload.getNamaPerusahaan());
            Integer userPerusahaan= tabelPerusahaanRepository.validasiNamaUserPerusahaan(payload.getNamaPerusahaan(),userName);

            if(namaPerusahaan==0){
                msg.setStatus(false);
                msg.setMessage("perusahaan not registered");
                return ResponseEntity.status(HttpStatus.OK).body(msg);
            }else if(userPerusahaan==0){
                msg.setStatus(false);
                msg.setMessage("perusahaan can't access with your account, please change account first");
                return ResponseEntity.status(HttpStatus.OK).body(msg);
            }
            tabelBarang dataBarang= tabelBarangRepository.getBarangByName(payload.getNamaBarang());
            if(dataBarang==null){
                msg.setStatus(false);
                msg.setMessage("barang not registered");
                return ResponseEntity.status(HttpStatus.OK).body(msg);
            }else if(dataBarang.getStock()==0 || dataBarang.getStock()< payload.getTotalBarang()){
                msg.setStatus(false);
                msg.setMessage("stock barang empty or not enough");
                return ResponseEntity.status(HttpStatus.OK).body(msg);
            }

            payload.setIdTransaksi(String.valueOf(UUID.randomUUID()));
            payload.setTanggalTransaksi(new Date());
            payload.setHargaBarang(dataBarang.getHarga());
            payload.setGrandTotal(dataBarang.getHarga()*payload.getTotalBarang());
            payload.setSisaBarang(dataBarang.getStock()-payload.getTotalBarang());
            tabelTransaksiRepository.save(payload);
            dataBarang.setStock(dataBarang.getStock()-payload.getTotalBarang());
            tabelBarangRepository.save(dataBarang);
            msg.setStatus(true);
            msg.setMessage("success");
            result.put("transaksi", payload);
            msg.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    public List<tabelTransaksi> listAllTransaction() {
        return tabelTransaksiRepository.findAll();
    }


}
