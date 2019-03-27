/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author m.j
 */
@Entity
@Table(name = "Item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByCode", query = "SELECT i FROM Item i WHERE i.code = :code"),
    @NamedQuery(name = "Item.findByName", query = "SELECT i FROM Item i WHERE i.name = :name"),
    @NamedQuery(name = "Item.findByUnitPrice", query = "SELECT i FROM Item i WHERE i.unitPrice = :unitPrice"),
    @NamedQuery(name = "Item.findByBalance", query = "SELECT i FROM Item i WHERE i.balance = :balance"),
    @NamedQuery(name = "Item.findByFloor", query = "SELECT i FROM Item i WHERE i.floor = :floor")})
public class Item implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "code")
    private Integer code;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "unitPrice")
    private int unitPrice;
    @Column(name = "balance")
    private Short balance;
    @Column(name = "floor")
    private Short floor;

    public Item() {
    }

    public Item(String Name) {
        this.name = Name;
    }

    public Item( String name, int unitPrice) {
      
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        Integer oldCode = this.code;
        this.code = code;
        changeSupport.firePropertyChange("code", oldCode, code);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        int oldUnitPrice = this.unitPrice;
        this.unitPrice = unitPrice;
        changeSupport.firePropertyChange("unitPrice", oldUnitPrice, unitPrice);
    }

    public Short getBalance() {
        return balance;
    }

    public void setBalance(Short balance) {
        Short oldBalance = this.balance;
        this.balance = balance;
        changeSupport.firePropertyChange("balance", oldBalance, balance);
    }

    public Short getFloor() {
        return floor;
    }

    public void setFloor(Short floor) {
        Short oldFloor = this.floor;
        this.floor = floor;
        changeSupport.firePropertyChange("floor", oldFloor, floor);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Item[ code=" + code + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
