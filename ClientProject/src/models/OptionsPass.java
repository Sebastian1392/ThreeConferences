package models;

public enum OptionsPass {

    ACCEPT("Accept"),REJECT("Reject");

    private String text;

    private OptionsPass(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
