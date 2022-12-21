package servers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Assistant;
import models.Conference;
import models.ConferencesPermission;
import models.IOConference;
import models.IPermission;
import models.Lecturer;
import models.Option;
import models.OptionsCoferences;
import models.OptionsPass;
import models.Root;
import models.SubTopic;
import models.Topic;
import models.User;
import persistence.FileManagerServer;
import persistence.JSONManagerServer;
import persistence.PDFManager;
import persistence.XMLTreeManager;

public class Server {

    private static final String ROOT_NAME_CONFERENCE = "Conferences Root";
    private static final String ROOT_NAME_PERMISSIONS = "Permissions Root";
    private static final int CLIENTS_PORT = 3000;
    private static final int CLOSE_PORT = 3001;
    private static final int ACTION_PORT = 3002;
    private static final int PERMISSIONS_PORT = 3003;
    private static final String MESSAGE_INIT_SEVER = "Servidor iniciado en puerto 3000";
    private static final String MESSAGE_INIT_SEVER_CLOSE = "Servidor iniciado en puerto 3001";
    private static final String MESSAGE_INIT_SEVER_ACTION = "Servidor iniciado en puerto 3002";
    private static final String MESSAGE_INIT_SEVER_PERMISSIONS = "Servidor iniciado en puerto 3003";
    private static final String MESSAGE_CONNECTION_CLIENT = "Conexion iniciada en puerto 3000";
    private static final String MESSAGE_CLOSE_CONNECTIONS = "Conexion iniciada en puerto 3001";
    private static final String MESSAGE_ACTION_CONNECTIONS = "Conexion iniciada en puerto 3002";
    private static final String MESSAGE_PERMISSIONS_CONNECTIONS = "Conexion iniciada en puerto 3003";
    private static final String USER = "User";
    private static final String ADMIN = "Admin";
    private static final String UPDATE_TREE_CONFERENCES = "Update Tree Conferences";
    private static final String UPDATE_TREE_PERMISSIONS = "Update Tree Permissions";
    private static final String PERMISSION = "Permission";
    private static final String ADD = "Add";
    private static final String DELETE = "Delete";
    private static final String REPORT = "Report";

    private ServerSocket serverClients;
    private ServerSocket serverClose;
    private ConnectionUser admin;
    private NodeTree<IOConference> conferenceTree;
    private NodeTree<IPermission> permissionTree;
    private ConcurrentMap<Long,ConnectionUser> connections;
    private boolean severClientsListen;
    private ServerSocket serverAction;
    private ServerSocket serverPermissions;

    public Server() throws IOException {
        serverClients = new ServerSocket(CLIENTS_PORT);
        Logger.getGlobal().log(Level.INFO, MESSAGE_INIT_SEVER);
        connections = new ConcurrentHashMap<>();
        conferenceTree = new NodeTree<>(new Root(ROOT_NAME_CONFERENCE));
        permissionTree = new NodeTree<>(new Root(ROOT_NAME_PERMISSIONS));
        severClientsListen = true;
        manageClientConnections();
        manageCloseConnections();
        manageActionsConnections();
        managePermissionsConnections();
        addNodes();
        printTree();
    }
    
    private void manageClientConnections(){
        new Thread(new Runnable(){
			@Override
			public void run() {
				while(severClientsListen){
                    try {
                        manageNewConnection(serverClients.accept());
                        Logger.getGlobal().log(Level.INFO, MESSAGE_CONNECTION_CLIENT);
                        Thread.sleep(1000);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
			}
        }).start();
    }

    private void manageCloseConnections() throws IOException {
        serverClose = new ServerSocket(CLOSE_PORT);
        Logger.getGlobal().log(Level.INFO, MESSAGE_INIT_SEVER_CLOSE);
        new Thread(new Runnable(){
			@Override
			public void run() {
				while(severClientsListen){
                    try {
                        getTypeUser(serverClose.accept());
                        Logger.getGlobal().log(Level.INFO, MESSAGE_CLOSE_CONNECTIONS);
                        Thread.sleep(1000);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
			}
        }).start();
    }

    private void manageActionsConnections() throws IOException {
        serverAction = new ServerSocket(ACTION_PORT);
        Logger.getGlobal().log(Level.INFO, MESSAGE_INIT_SEVER_ACTION);
        new Thread(new Runnable(){
			@Override
			public void run() {
				while(severClientsListen){
                    try {
                        manageActionTree(serverAction.accept());
                        Logger.getGlobal().log(Level.INFO, MESSAGE_ACTION_CONNECTIONS);
                        Thread.sleep(1000);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
			}
        }).start();
    }

    private void managePermissionsConnections() throws IOException {
        serverPermissions = new ServerSocket(PERMISSIONS_PORT);
        Logger.getGlobal().log(Level.INFO, MESSAGE_INIT_SEVER_PERMISSIONS);
        new Thread(new Runnable(){
			@Override
			public void run() {
				while(severClientsListen){
                    try {
                        managePermissions(serverPermissions.accept());
                        Logger.getGlobal().log(Level.INFO, MESSAGE_PERMISSIONS_CONNECTIONS);
                        Thread.sleep(1000);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
			}
        }).start();
    }

    public void addToConferenceTree(NodeTree<IOConference> father,IOConference childData){
        if(father.getData().isFather()){
            NodeTree<IOConference> newChild = new NodeTree<IOConference>(childData);
            father.addChild(newChild);
        }
    }

    public void addToPermissionTree(NodeTree<IPermission> father,IPermission childData){
        if(father.getData().isFather()){
            NodeTree<IPermission> newChild = new NodeTree<IPermission>(childData);
            father.addChild(newChild);
        }
    }

    public NodeTree<IPermission> searchToPermissionTree(String name){
        return searchPermission(permissionTree,name);
    }

    public NodeTree<IOConference> searchToConferenceTree(String name){
        return searchConference(conferenceTree,name);
    }

    public NodeTree<IPermission> searchInSpecificPermissionNode(NodeTree<IPermission> root,String name){
        return searchPermission(root,name);
    }

    public NodeTree<IOConference> searchInSpecificConferenceNode(NodeTree<IOConference> root,String name){
        return searchConference(root,name);
    }
    
    public void deleteToConferenceTree(String name){
        deleteConference(conferenceTree,name);
    }

    public void deleteToPermissionsTree(String name){
        deletePermissions(permissionTree,name);
    }

    private boolean containsPermission(NodeTree<IPermission> root,String name){
        return searchPermission(root,name) != null;
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

    private NodeTree<IPermission> searchPermission(NodeTree<IPermission> base, String name) {
        if(base.getData().getName().equals(name)){
            return base;
        }else{
            for (NodeTree<IPermission> child : base.getChildren()) {
                NodeTree<IPermission> result = searchPermission(child,name);
                if(result != null){
                    return result;
                }
            }
        }
        return null;
    }

    private void deleteConference(NodeTree<IOConference> base, String name){
        for (NodeTree<IOConference> child : base.getChildren()) {
            if(child.getData().getName().equals(name)){
                base.deleteChild(child);
                break;
            }else{
                deleteConference(child,name);
            }
        }
    }

    private void deletePermissions(NodeTree<IPermission> base, String name){
        for (NodeTree<IPermission> child : base.getChildren()) {
            if(child.getData().getName().equals(name)){
                base.deleteChild(child);
                break;
            }else{
                deletePermissions(child,name);
            }
        }
    }

    private void addNodes(){
        addToConferenceTree(conferenceTree, new Topic("Programacion"));
        addToConferenceTree(conferenceTree, new Topic("Juegos"));
        addToConferenceTree(searchToConferenceTree("Juegos"), new SubTopic("xbox"));
        addToConferenceTree(searchToConferenceTree("Juegos"), new SubTopic("play"));
        addToConferenceTree(searchToConferenceTree("Programacion"), new SubTopic("Java"));
        addToConferenceTree(searchToConferenceTree("Programacion"), new Conference("Buenas Practicas"));
        addToConferenceTree(searchToConferenceTree("Java"), new Conference("Hilos en Java"));
        addToConferenceTree(searchToConferenceTree("Hilos en Java"), new Assistant("Jose"));
        addToConferenceTree(searchToConferenceTree("Hilos en Java"), new Lecturer("Sebastian"));
    }
    
    public void printSpecificTree(NodeTree<IOConference> conferenceTree){
        print(conferenceTree);
    }

    public void printTree(){
        print(conferenceTree);
    }

    private void print(NodeTree<IOConference> base) {
        System.out.println(base.getData().getName());
        for (NodeTree<IOConference> child : base.getChildren()) {
            print(child);
        }
    }

    private void manageNewConnection(Socket socket) throws IOException {
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        String[] dataConnection = JSONManagerServer.readUserName(inputStream.readUTF());
        switch (dataConnection[0]) {
            case USER:
                manageUserConnection(dataConnection[1],socket);
                break;
            case ADMIN:
                manageAdminConnecion(dataConnection[1],socket);
            break;
        }
    }

    private void manageAdminConnecion(String adminName,Socket socket) throws IOException {
        if(admin == null){
            admin = new ConnectionUser(adminName,socket);
            updateTreeToAdmin(true);
            updateTreeToAdmin(false);
        }
    }

    private void manageUserConnection(String userName, Socket socket) throws IOException {
        ConnectionUser connection = new ConnectionUser(userName,socket);
        updateTreeToUser(connection);
        connections.put((long)connections.size(), connection);
        addToPermissionTree(permissionTree, new User(userName));
        updateTreeToAdmin(false);
    }

    private void updateTreeUsersAndAdmin() throws IOException {
        for (long key : connections.keySet()) {
            ConnectionUser user = connections.get(key);
            if(user.isConnected()){
                updateTreeToUser(user);
            }
        }
        updateTreeToAdmin(true);
    }

    private void updateTreeToUser(ConnectionUser user) throws IOException {
        user.senTypeData(JSONManagerServer.writeTypeData(UPDATE_TREE_CONFERENCES));
        user.sendTree(XMLTreeManager.writeConferencesTree(conferenceTree));
    }

    private void updateTreeToAdmin(boolean isConference) throws IOException {
        if(admin != null){
            if(isConference){
                admin.senTypeData(JSONManagerServer.writeTypeData(UPDATE_TREE_CONFERENCES));
                admin.sendTree(XMLTreeManager.writeConferencesTree(conferenceTree));
            }else{
                admin.senTypeData(JSONManagerServer.writeTypeData(UPDATE_TREE_PERMISSIONS));
                admin.sendTree(XMLTreeManager.writePermissionsTree(permissionTree));
            }
        }
    }

    private void getTypeUser(Socket socket) throws IOException {
        DataInputStream input = new DataInputStream(socket.getInputStream());
        String[] data = JSONManagerServer.readUserName(input.readUTF());
        switch (data[0]) {
            case USER:
                closeUserConnection(data[1]);
                break;
            case ADMIN:
                admin = null;
            break;
            case REPORT:
                generateNodeReport(socket,data[1]);
            break;
        }
        socket.close();
    }

    private void generateNodeReport(Socket socket ,String nodeName) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        String path = PDFManager.generatePdf(searchToConferenceTree(nodeName));
        byte[] file = FileManagerServer.readFileReport(path);
        outputStream.writeUTF(JSONManagerServer.writeFileName(path));
        outputStream.writeInt(file.length);
        outputStream.write(file);
    }

    private void closeUserConnection(String userName) throws IOException {
        for (long key : connections.keySet()) {
            ConnectionUser user = connections.get(key);
            if(user.isConnected()){
                if(user.getUserName().equals(userName)){
                    connections.remove(key);
                    break;
                }
            }
        }
    }

    private void manageActionTree(Socket socket) throws IOException {
        DataInputStream input = new DataInputStream(socket.getInputStream());
        String[] data = JSONManagerServer.readActionConferenceTree(input.readUTF());
        switch (data[0]) {
            case UPDATE_TREE_CONFERENCES:
                manageActionConferencesTree(data);
                break;
            case UPDATE_TREE_PERMISSIONS:
                manageActionPermissionsTree(data);
            break;
        }
        socket.close();
    }

    private void manageActionConferencesTree(String[] data) throws IOException {
        switch (data[1]) {
            case ADD:
                addConference(data);
                break;
            case DELETE:
                deleteToConferenceTree(data[3]);
                break;
        }
        updateTreeUsersAndAdmin();
    }

    private void addConference(String[] data) throws IOException {
        switch (OptionsCoferences.valueOf(data[2])) {
            case ADD_ASSISTANT:
                addToConferenceTree(searchToConferenceTree(data[3]), new Assistant(data[4]));
                break;
            case ADD_CONFERENCE:
                addToConferenceTree(searchToConferenceTree(data[3]), new Conference(data[4]));
                break;
            case ADD_LECTURER:
                addToConferenceTree(searchToConferenceTree(data[3]), new Lecturer(data[4]));
                break;
            case ADD_SUBTOPIC:
                addToConferenceTree(searchToConferenceTree(data[3]), new SubTopic(data[4]));
                break;
            case ADD_TOPIC:
                addToConferenceTree(searchToConferenceTree(data[3]), new Topic(data[4]));
                break;
            default:
                break;
        }
    }

    private void managePermissions(Socket socket) throws IOException {
        DataInputStream input = new DataInputStream(socket.getInputStream());
        if(admin != null){
            admin.senTypeData(JSONManagerServer.writeTypeData(PERMISSION));
            admin.sendUserAndNodePermission(input.readUTF());
        }
        socket.close();
    }

    private void manageActionPermissionsTree(String[] data) throws IOException {
        NodeTree<IOConference> result = searchToConferenceTree(data[4]);
        ArrayList<String[]> nodes = new ArrayList<>();
        getDataChildren(result,data[2],nodes);
        for (String[] strings : nodes) {
            addPermissionsToTree(data[3],strings[0],strings[1]);
            addPermissionsToUsers(data[3],strings[0],strings[1]);
        }
        updateTreeToAdmin(false);
    }

    private void addPermissionsToTree(String fatherName, String childName, String option){
        NodeTree<IPermission> result = searchToPermissionTree(fatherName);
        NodeTree<IPermission> child = searchInSpecificPermissionNode(result,childName);
        if(child == null){
            child = new NodeTree<IPermission>(new ConferencesPermission(childName));
            child.addChild(new NodeTree<IPermission>(new Option(OptionsCoferences.valueOf(option).getText())));
            result.addChild(child);
        }else{
            if(!containsPermission(child, OptionsCoferences.valueOf(option).getText())){
                child.addChild(new NodeTree<IPermission>(new Option(OptionsCoferences.valueOf(option).getText())));
            }
        }
    }

    private void addPermissionsToUsers(String fatherName, String childName, String option) throws IOException {
        for (long key : connections.keySet()) {
            ConnectionUser user = connections.get(key);
            if(user.isConnected()){
                if(user.getUserName().equals(fatherName)){
                    user.senTypeData(JSONManagerServer.writeTypeData(PERMISSION));
                    user.sendPermission(JSONManagerServer.writePermissionToUser(childName, option));
                    break;
                }
            }
        }
    }

    private void getDataChildren(NodeTree<IOConference> base,String option,ArrayList<String[]> dataNodes){
        getAddOptions(base,option,dataNodes);
        for (NodeTree<IOConference> child : base.getChildren()) {
            getDataChildren(child,option,dataNodes);
        }
    }

    private String getTypeOption(String option,OptionsCoferences optionNode){
        String result = null;
        switch (OptionsPass.valueOf(option)) {
            case ADD:
                result = getAddNodeOption(optionNode);
                break;
            case DELETE:
                result = getDeleteNodeOption(optionNode);
                break;
            case REPORT:
                result = (optionNode.equals(OptionsCoferences.GET_REPORT))? optionNode.toString(): null;
                break;
        }
        return result;
    }

    private void getAddOptions(NodeTree<IOConference> base,String option,ArrayList<String[]> dataNodes) {
        for (OptionsCoferences optionNode : base.getData().getOptions()) {
            String optionResult = getTypeOption(option,optionNode);
            if(optionResult != null){
                dataNodes.add(new String[]{base.getData().getName(),optionResult});
            }
        }
    }

    private String getAddNodeOption(OptionsCoferences optionNode){
        switch (OptionsCoferences.valueOf(optionNode.toString())) {
            case ADD_ASSISTANT:
            case ADD_CONFERENCE:
            case ADD_LECTURER:
            case ADD_SUBTOPIC:
            case ADD_TOPIC:
               return optionNode.toString();
            default:
                break;
        }
        return null;
    }

    private String getDeleteNodeOption(OptionsCoferences optionNode){
        switch (OptionsCoferences.valueOf(optionNode.toString())) {
            case DELETE_ASSISTANT:
            case DELETE_CONFERENCE:
            case DELETE_LECTURER:
            case DELETE_SUBTOPIC:
            case DELETE_TOPIC:
               return optionNode.toString();
            default:
                break;
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
