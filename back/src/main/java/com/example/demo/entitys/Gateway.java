package com.example.demo.entitys;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "gateway")
public class Gateway {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "serial", nullable = false, unique = true)
    private String snumber;
    private String name;

    private String ipaddr;

    @OneToMany(mappedBy = "gateway", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Peripheral> peripheral;

    public Gateway(){}

    public Gateway(Long id, String snumber, String name, String ipaddr, Set<Peripheral> peripheral) {
        this.id = id;
        this.snumber = snumber;
        this.name = name;
        this.ipaddr = ipaddr;
        this.peripheral= peripheral;
    }

    public Set<Peripheral> getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(Set<Peripheral> peripheral) {
        this.peripheral = peripheral;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getSnumber() {
        return snumber;
    }

    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

}
