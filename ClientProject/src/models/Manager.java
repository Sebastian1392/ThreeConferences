package models;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import clients.Client;
import presenters.IObserver;

public class Manager {

    private static final String EMPTY = "";
    private static final String ADD = "Add";
    private static final String DELETE = "Delete";

    private NodeTree<IOConference> root;
    private User user;
    private Client client;

    public void initConnection(IObserver presenter, String userName) throws UnknownHostException, IOException {
        user = new User(userName);
        client = new Client(presenter);
        client.sendUserName(user.getUserName());
    }

    public NodeTree<IOConference> getRoot(){
        return root;
    }

    public void generateTree(ArrayList<String[]> dataNodes){
        for (String[] data : dataNodes) {
            switch (data[0]) {
                case TagConstants.ROOT_TAG:
                    root = new NodeTree<>(new Root(data[2]));
                break;
                case TagConstants.TOPIC_TAG:
                    addToConferenceTree(searchNode(data[1]), new Topic(data[2]));
                break;
                case TagConstants.SUB_TOPIC_TAG:
                    addToConferenceTree(searchNode(data[1]), new SubTopic(data[2]));
                break;
                case TagConstants.CONFERENCE_TAG:
                    addToConferenceTree(searchNode(data[1]), new Conference(data[2]));
                break;
                case TagConstants.LECTURER_TAG:
                    addToConferenceTree(searchNode(data[1]), new Lecturer(data[2]));
                break;
                case TagConstants.ASSISTANT_TAG:
                    addToConferenceTree(searchNode(data[1]), new Assistant(data[2]));
                break;
            }
        }
        addPermissionsToNodes();
    }

    public void addToConferenceTree(NodeTree<IOConference> father,IOConference childData){
        if(father.getData().isFather()){
            NodeTree<IOConference> newChild = new NodeTree<IOConference>(childData);
            father.addChild(newChild);
        }
    }

    public NodeTree<IOConference> searchNode(String name){
        return searchConference(root,name);
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

    public void closeConnection() throws UnknownHostException, IOException {
        if(client != null){
            client.closeConnection(user.getUserName());
        }
    }

    public OptionsCoferences[] getOptionsNode(String nodeName){
        return searchNode(nodeName).getData().getOptions();
    }

    public void addConferenceNode(String option, String fatherName, String childName)
    throws UnknownHostException, IOException {
        client.actionConferenceTree(OptionsCoferences.getOptionSelected(option).toString(),ADD,fatherName, childName);
    }

    public void deleteConferenceNode(String nodeName) throws UnknownHostException, IOException {
        deleteNodeToPermissions(nodeName);
        client.actionConferenceTree(EMPTY,DELETE,nodeName,EMPTY);
    }

    public void requestPermissions(String fatherName) throws UnknownHostException, IOException {
        client.requestPermissions(user.getUserName(), fatherName);
    }

    public void addPermissionTolist(String nodeName,String option){
        boolean contains = false;
        ArrayList<Permission> permissions = user.getPassList();
        for (Permission permission : permissions) {
            if(permission.getNodeName().equals(nodeName)){
                if(!permission.getOptionsAvailables().contains(OptionsCoferences.valueOf(option))){
                    permission.addOption(OptionsCoferences.valueOf(option));
                    contains = true;
                    break;
                }
            }
        }
        if(!contains){
            user.addPermission(nodeName, OptionsCoferences.valueOf(option));
        }
        addPermissionsToNodes();
    }

    private void addPermissionsToNodes(){
        ArrayList<Permission> permissions = user.getPassList();
        for (Permission permission : permissions) {
            NodeTree<IOConference> result = searchNode(permission.getNodeName());
            // if(result != null){
                result.getData().setOptions((converToOptionsArray(permission.getOptionsAvailables())));
            // }
        }
    }

    public void print(){
        ArrayList<Permission> permissions = user.getPassList();
        for (Permission permission : permissions) {
            System.out.println(permission.getNodeName());
            for (OptionsCoferences var : permission.getOptionsAvailables()) {
                System.out.println("\t" + var);
            }
        }
    }

    private OptionsCoferences[] converToOptionsArray(ArrayList<OptionsCoferences> options){
        OptionsCoferences[] newOptions = new OptionsCoferences[options.size()];
        for (int i = 0; i < options.size(); i++) {
            newOptions[i] = options.get(i);
        }
        return newOptions;
    } 

    public HashMap<String,ImageIcon> getNodePermissions(){
        HashMap<String,ImageIcon> hashMap = new HashMap<>();
        ArrayList<Permission> permissions = user.getPassList();
        for (Permission permission : permissions) {
            NodeTree<IOConference> result = searchNode(permission.getNodeName());
            if(result != null){
                hashMap.put(permission.getNodeName(), result.getData().getIcon());
            }
        }
        return hashMap;
    }

    private void deleteNodeToPermissions(String nodeName){
        ArrayList<Permission> permissions = user.getPassList();
        for (Permission permission : permissions) {
            if(permission.getNodeName().equals(nodeName)){
                permissions.remove(permission);
                break;
            }
        }
    }

    public void sendRequestReport(String nodeName) throws UnknownHostException, IOException {
        client.requestReport(nodeName);
    }
}
