/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.uas2020.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author STRIX
 */
@Entity
@Table(name = "penduduk")
@NamedQueries({
    @NamedQuery(name = "Penduduk.findAll", query = "SELECT p FROM Penduduk p"),
    @NamedQuery(name = "Penduduk.findById", query = "SELECT p FROM Penduduk p WHERE p.id = :id"),
    @NamedQuery(name = "Penduduk.findByNama", query = "SELECT p FROM Penduduk p WHERE p.nama = :nama"),
    @NamedQuery(name = "Penduduk.findByNik", query = "SELECT p FROM Penduduk p WHERE p.nik = :nik"),
    @NamedQuery(name = "Penduduk.findByAlamat", query = "SELECT p FROM Penduduk p WHERE p.alamat = :alamat")})
public class Penduduk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nama")
    private String nama;
    @Column(name = "NIK")
    private Integer nik;
    @Column(name = "alamat")
    private String alamat;
    @Lob
    @Column(name = "foto")
    private byte[] foto;

    public Penduduk() {
    }

    public Penduduk(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getNik() {
        return nik;
    }

    public void setNik(Integer nik) {
        this.nik = nik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Penduduk)) {
            return false;
        }
        Penduduk other = (Penduduk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pws.uas2020.model.entity.Penduduk[ id=" + id + " ]";
    }
    
}
