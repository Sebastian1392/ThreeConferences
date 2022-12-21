package models;

import javax.swing.ImageIcon;

public interface IOConference {

    public boolean isFather();

    public String getName();

    public String getType();

    public OptionsCoferences[] getOptions();

    public ImageIcon getIcon(); 
    
}
