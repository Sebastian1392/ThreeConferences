package models;

public enum OptionsPass {

    ADD("Add"),DELETE("Delete"),REPORT("Report");

    private String text;

    private OptionsPass(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static OptionsPass getOptionSelected(String text){
        for (OptionsPass option : OptionsPass.values()) {
            if(option.getText().equals(text)){
                return option;
            }
        }
        return null;
    }
}
