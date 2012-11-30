package com.paymentgateway.paymentgateway.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "EHASHES")
public class ConfirmationHash implements Serializable {
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "TYPE")
    private int type;
    @Column(name = "ID_TO_CONFIRM")
    private long idToConfirm;
    @Column(name = "hash_value")
    private String hash;

    public long getIdToConfirm() {
        return idToConfirm;
    }

    public void setIdToConfirm(long idToConfirm) {
        this.idToConfirm = idToConfirm;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
