package org.linlinjava.litemall.kudo.vo;

import java.util.Map;

public class FooterVo {

    private String tel;

    private String email;

    private String address;

    private String qrCode;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public FooterVo(Map<String, String> data) {
        this.tel = data.get("litemall_mall_phone");
        this.email = data.get("litemall_mall_email");
        this.address = data.get("litemall_mall_address");
        this.qrCode = data.get("litemall_mall_qrcode");
    }
}
