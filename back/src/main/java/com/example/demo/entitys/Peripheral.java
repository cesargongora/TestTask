package com.example.demo.entitys;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.utils.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "peripheral")
public class Peripheral {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double uid;
    private String vendor;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateat;
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="gateway_id", nullable = false)
    @JsonBackReference
    private Gateway gateway;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getUid() {
        return uid;
    }

    public void setUid(Double uid) {
        this.uid = uid;
    }

    public Status getStatus() {
        return status;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Date getUpdateAt() {
        return updateat;
    }

    public void setUpdateAt(Date updateat) {
        this.updateat = updateat;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public Peripheral() {
    }

    public Peripheral(Long id, Double uid, String vendor, Status status) {
        this.id = id;
        this.uid = uid;
        this.vendor = vendor;
        this.status = status;
    
    }
    @PrePersist
    public void PrePersist(){
        this.updateat=new Date();
    }
}
