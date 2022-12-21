package models;

public class User implements IPermission{

    private String userName;

    public User(String userName){
        this.userName = userName;
    } 

    @Override
    public boolean isFather() {
        return true;
    }

    @Override
    public String getName() {
        return userName;
    }

    @Override
    public String getTag() {
        return TagConstants.USER_TAG;
    }

}
