package com.example.demo.button;

import java.util.List;

public class SubButton extends AbstractButton{
    public SubButton(String name) {
        super(name);
    }

    public List<AbstractButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<AbstractButton> sub_button) {
        this.sub_button = sub_button;
    }

    private List<AbstractButton> sub_button;


}
