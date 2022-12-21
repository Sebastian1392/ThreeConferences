package models;

import javax.swing.ImageIcon;

public interface IOConference {

    public boolean isFather();

    public String getName();

    public String getTag();

    public void setOptions(OptionsCoferences[] options);

    public OptionsCoferences[] getOptions();

    public OptionsCoferences[] getAllOptions();

    public ImageIcon getIcon(); 
    
}
