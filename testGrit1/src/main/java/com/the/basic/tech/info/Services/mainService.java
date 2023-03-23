package com.the.basic.tech.info.Services;

import com.the.basic.tech.info.Utility.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.the.basic.tech.info.Entity.*;
import com.the.basic.tech.info.Repositories.*;
import java.util.Date;
import java.util.*;


@Service
public class mainService {

    @Autowired
    private mainRepository mainRepository;

    public ResponseEntity<MessageModel> fibonacci(int input)
    {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();

        try{
            // Membuat deret Fibonacci descending dan tanpa genap
            ArrayList<String> fibonacci = new ArrayList<>();
            int a = 0, b = 1;
            fibonacci.add(String.valueOf(b));
            for (int i = 0; i < input; i++) {
                int c = a + b;
                a = b;
                b = c;
                if (c % 2 == 1) {
                    fibonacci.add(String.valueOf(c));
                }
            }
            // reverse array
            ArrayList<String> reversedList = new ArrayList<String>();
            for (int i = fibonacci.size() - 1; i >= 0; i--) {
                reversedList.add(fibonacci.get(i));
            }

            String finalResult ="";
            for (String s: reversedList) {
                finalResult= finalResult+s+" ";
            }

            msg.setStatus(true);
            msg.setMessage("Success");
            result.put("output", finalResult.trim());
            msg.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(msg);

        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    public ResponseEntity<MessageModel> longestWord(String input)
    {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();

        try{
            String str = "abcdefghijklmnopqrstuvwxyz";
            int intTemp;
            ArrayList<String>temp = new ArrayList<>();
            ArrayList<String>temp2 = new ArrayList<>();
            ArrayList<Integer>temp3 = new ArrayList<>();
            for (int i = 0; i <= str.length(); i++) {
                for(int j = i+1;j<= str.length(); j++){

                    String substr = str.substring(i, j);
                    temp.add(substr);
                }
            }
            for (int i = 0; i <= input.length(); i++) {
                for(int j = i+1;j<= input.length(); j++){
                    String subinput = input.substring(i, j);
                    temp2.add(subinput);
                }
            }
            for (int i = 0; i < temp.size(); i++) {
                String value = temp.get(i);
                if (temp2.contains(value)) {
                    temp3.add(value.length());
                }
            }
            for (int i = 0; i <temp3.size(); i++) {
                for (int j = i+1; j <temp3.size(); j++) {
                    if(temp3.get(i) >temp3.get(j)) {
                        intTemp = temp3.get(i);
                        temp3.set(i, temp3.get(j)) ;
                        temp3.set(j,intTemp);
                    }
                }
            }
            int lastIndex = temp3.size() - 1;
            int longestWord = temp3.get(lastIndex);

            msg.setStatus(true);
            msg.setMessage("Success");
            result.put("output", longestWord);
            msg.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(msg);

        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    public ResponseEntity getListData() {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();
        try{

            List<testGrit> data = mainRepository.getAllData();

            if(data.isEmpty()){
                msg.setStatus(true);
                msg.setMessage("data tidak ditemukan");
                msg.setData(null);
                return ResponseEntity.ok().body(msg);
            }else {
                msg.setStatus(true);
                msg.setMessage("Success");
                result.put("data", data);
                msg.setData(result);
                return ResponseEntity.ok().body(msg);
            }
        }catch (Exception e){
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.ok().body(msg);
        }
    }

    public ResponseEntity getData() {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();
        try{

            testGrit data = mainRepository.getLastData();

            if(data == null){
                msg.setStatus(true);
                msg.setMessage("data tidak ditemukan");
                msg.setData(null);
                return ResponseEntity.ok().body(msg);
            }else {
                msg.setStatus(true);
                msg.setMessage("Success");
                result.put("data", data);
                msg.setData(result);
                return ResponseEntity.ok().body(msg);
            }
        }catch (Exception e){
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.ok().body(msg);
        }
    }

}
