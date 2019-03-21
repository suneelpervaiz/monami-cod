package com.monami.secondroundassignment.modal;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * BaseDomainEnitity to declare fields to be in each domain table like id
 * and creational timestamps
 *
 *Created by BAK on 7/23/17.
 */
@MappedSuperclass
public abstract class BaseDomainEnitity implements Serializable {


    //@XmlElement(type = Object.class)
    @Id
    @NotNull
    @GeneratedValue
    @Column(name="generated_ID")
    protected Long id;

    @Version
    private int version;

    @Temporal(TemporalType.DATE)
    @Column
    protected Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    protected Date updatedAt;

    @NotNull
    @Column(name = "biz_key")
    private String bizKey;


    public BaseDomainEnitity() {
        createdAt = new Date();
        updatedAt = new Date();
        bizKey = UUID.randomUUID().toString();
    }


    /**
     * To make XStream deserialization assign values to
     * base class fields of createdAt and updatedAt
     *
     * @return
     */
    public Object readResolve() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
            this.updatedAt = createdAt;
        }

        return this;
    }

    public int getVersion() {
        return version;
    }
    public boolean isNew() {
        return id == null;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }



    public Date getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }



    public Date getUpdatedAt() {
        return updatedAt;
    }


    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    public String getBizKey() {
        return bizKey;
    }

    public void setBizKey(String bizKey) {
        this.bizKey = bizKey;
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDomainEnitity that = (BaseDomainEnitity) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (getBizKey() != null ? !getBizKey().equals(that.getBizKey()) : that.getBizKey() != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (bizKey != null ? bizKey.hashCode() : 0);
        return result;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
