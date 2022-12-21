package models;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import admins.Admin;
import presenters.IObserver;

public class Manager {

    private NodeTree<IOConference> rootConference;
    private NodeTree<IPermission> rootPermissons;
    private String adminName;
    private Admin admin;

    public void initConnection(IObserver presenter, String adminName) throws UnknownHostException, IOException {
        this.adminName = adminName;
        admin = new Admin(presenter);
        admin.sendUserName(adminName);
    }

    public NodeTree<IOConference> getConferencesRoot(){
        return rootConference;
    }

    public NodeTree<IPermission> getPermissionsRoot(){
        return rootPermissons;
    }

    public String getAdminName(){
        return adminName;
    }

    public void generateConferencesTree(ArrayList<String[]> dataNodes){
        for (String[] data : dataNodes) {
            switch (data[0]) {
                case TagConstants.ROOT_TAG:
                    rootConference = new NodeTree<>(new Root(data[2]));
                break;
                case TagConstants.TOPIC_TAG:
                    addToConferenceTree(searchConferenceNode(data[1]), new Topic(data[2]));
                break;
                case TagConstants.SUB_TOPIC_TAG:
                    addToConferenceTree(searchConferenceNode(data[1]), new SubTopic(data[2]));
                break;
                case TagConstants.CONFERENCE_TAG:
                    addToConferenceTree(searchConferenceNode(data[1]), new Conference(data[2]));
                break;
                case TagConstants.LECTURER_TAG:
                    addToConferenceTree(searchConferenceNode(data[1]), new Lecturer(data[2]));
                break;
                case TagConstants.ASSISTANT_TAG:
                    addToConferenceTree(searchConferenceNode(data[1]), new Assistant(data[2]));
                break;
            }
        }
    }

    public void addToConferenceTree(NodeTree<IOConference> father,IOConference childData){
        if(father.getData().isFather()){
            NodeTree<IOConference> newChild = new NodeTree<IOConference>(childData);
            father.addChild(newChild);
        }
    }

    public NodeTree<IOConference> searchConferenceNode(String name){
        return searchConference(rootConference,name);
    }

    private NodeTree<IOConference> searchConference(NodeTree<IOConference> base, String name) {
        if(base.getData().getName().equals(name)){
            return base;
        }else{
            for (NodeTree<IOConference> child : base.getChildren()) {
                NodeTree<IOConference> result = searchConference(child,name);
                if(result != null){
                    return result;
                }
            }
        }
        return null;
    }

    public void generatePermissionsTree(ArrayList<String[]> dataNodes){
        User user = null;
        for (String[] data : dataNodes) {
            switch (data[0]) {
                case TagConstants.ROOT_TAG:
                    rootPermissons = new NodeTree<>(new RootPermissions(data[2]));
                break;
                case TagConstants.USER_TAG:
                    user = new User(data[2]);
                    addToPermissionsTree(searchPermissionsNode(data[1]), user);
                break;
                case TagConstants.CONFERENCES_PERMISSION_TAG:
                    addToPermissionsTree(searchPermissionsNode(data[1]), new ConferencesPermission(data[2]));
                break;
                case TagConstants.OPTION_TAG:
                    addToPermissionsTree(getConference(user.getName(),data[1]), new Option(data[2]));
                break;
            }
        }
    }

    private NodeTree<IPermission> getConference(String userName,String coferenceName){
        NodeTree<IPermission> result = searchPermissionsNode(userName);
        return searchPermissionsEspecificNode(result,coferenceName);
    }

    public void addToPermissionsTree(NodeTree<IPermission> father,IPermission childData){
        if(father.getData().isFather()){
            NodeTree<IPermission> newChild = new NodeTree<IPermission>(childData);
            father.addChild(newChild);
        }
    }

    public NodeTree<IPermission> searchPermissionsNode(String name){
        return searchPermissions(rootPermissons,name);
    }

    public NodeTree<IPermission> searchPermissionsEspecificNode(NodeTree<IPermission> father,String name){
        return searchPermissions(father,name);
    }

    private NodeTree<IPermission> searchPermissions(NodeTree<IPermission> base, String name) {
        if(base.getData().getName().equals(name)){
            return base;
        }else{
            for (NodeTree<IPermission> child : base.getChildren()) {
                NodeTree<IPermission> result = searchPermissions(child,name);
                if(result != null){
                    return result;
                }
            }
        }
        return null;
    }

    public void closeConnection() throws UnknownHostException, IOException {
        if(admin != null){
            admin.closeConnection(adminName);
        }
    }

    public Object[] getDataNode(String nodeName){
        NodeTree<IOConference> result = searchConferenceNode(nodeName);
        return new Object[]{result.getData().getType() ,result.getData().getOptions()};
    }

    public void sendOptionPermission(String fatherName, String nodeName, String optionNode) throws UnknownHostException, IOException {
        admin.sendOptionPermission(fatherName,nodeName, OptionsPass.getOptionSelected(optionNode).toString());
    }
}
