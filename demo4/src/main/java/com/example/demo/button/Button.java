package com.example.demo.button;

import javax.swing.*;
import java.util.List;

public class Button {
    public List<AbstractButton> getButton() {
        return button;
    }

    public void setButton(List<AbstractButton> button) {
        this.button = button;
    }

    private List<AbstractButton> button;

}
