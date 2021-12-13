package com.example.bdsqlite.modelos.sucursales;

public class Sucursal {
    private String id;
    private String name;
    private String direction;
    private String phone;
    private byte[] image;

    public Sucursal(String id, String name, String direction, String phone, byte[] image) {
        this.id = id;
        this.name = name;
        this.direction = direction;
        this.phone = phone;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
