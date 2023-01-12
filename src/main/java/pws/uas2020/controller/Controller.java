/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.uas2020.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pws.uas2020.model.entity.Penduduk;
import pws.uas2020.model.jpa.PendudukJpaController;

/**
 *
 * @author STRIX
 */

@RestController
@CrossOrigin
@RequestMapping("/penduduk")
public class Controller {
    
    //manggil entity
    Penduduk kf = new Penduduk();
    //jpa
    PendudukJpaController ctrl = new PendudukJpaController();
    
    //get data mapping
    @GetMapping
    public List<Penduduk> getData(){
        List<Penduduk> data = new ArrayList<Penduduk>();
        try{
            data = ctrl.findPendudukEntities();
        }catch (Exception e){
            
        }
        return data;
    }
    
    
    //get mapping id
    @GetMapping("/{id}")
    public List<Penduduk> getKafeEntities(@PathVariable("id")int id){
        List<Penduduk> dataa = new ArrayList<Penduduk>(); //new object
        try{
            kf = ctrl.findPenduduk(id);
        dataa.add(kf);
        }catch (Exception e){}
        return dataa;
    }
    
    
    //post mapping
    @PostMapping
    public String insertData(HttpEntity<String> requestdata){
        String message = "Data berhasil masuk";
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            kf = map.readValue(json_receive, Penduduk.class);
            ctrl.create(kf);
        }catch(Exception e){
            message = "Data Gagal dimasukkan";
        }
        return message;
    }
    
    @PutMapping
    public String updateData(HttpEntity<String> requestdata){
        String message = "Data berhasil di edit";
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            kf = map.readValue(json_receive, Penduduk.class);
            ctrl.edit(kf);
        }catch(Exception e){
            message = "Data Gagal edit";
        }
        return message;
    }
    
    @DeleteMapping
    public String deleteData(HttpEntity<String> requestdata){
        String message = "Data berhasil dihapus";
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            kf = map.readValue(json_receive, Penduduk.class);
            ctrl.destroy(kf.getId());
        }catch(Exception e){
            message = "Data Gagal dihapus";
        }
        return message;
    }
}