package br.edu.ifsc.ddm.pdm_app_imc;

import android.graphics.drawable.Drawable;

public class Imc {
    private Drawable img;
    private String mensagem;

    public Imc(Drawable img, String msg){

        this.img = img;
        this.mensagem = msg;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
