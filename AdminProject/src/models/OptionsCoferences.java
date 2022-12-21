package models;

public enum OptionsCoferences {

    ADD_TOPIC("Add Topic"),DELETE_TOPIC("Delete Topic"),ADD_SUBTOPIC("Add Subtopic"),
    DELETE_SUBTOPIC("Delete Subtopic"),ADD_CONFERENCE("Add Conference"),DELETE_CONFERENCE("Delete Conference"),
    ADD_LECTURER("Add Lecturer"),ADD_ASSISTANT("Add Assistant"),DELETE_LECTURER("Delete Lecturer"),
    DELETE_ASSISTANT("Delete Assistant"),GET_REPORT("Get Report");

    private String text;

    private OptionsCoferences(String text) {
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public static OptionsCoferences getOptionSelected(String text){
        for (OptionsCoferences option : OptionsCoferences.values()) {
            if(option.getText().equals(text)){
                return option;
            }
        }
        return null;
    }
    
}
