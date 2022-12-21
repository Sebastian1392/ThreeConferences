package servers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import models.OptionsCoferences;

public class ConnectionUser {

    private String userName;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private ArrayList<Permission> permissions;

    public ConnectionUser(String userName, Socket socket) throws IOException {
        permissions = new ArrayList<>();
        this.userName = userName;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public ArrayList<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(String nodeName,OptionsCoferences option){
        permissions.add(new Permission(nodeName,option));
    }

    public String getUserName() {
        return userName;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isConnected(){
        return inputStream != null && outputStream != null;
    }

    public void senTypeData(String data) throws IOException {
        outputStream.writeUTF(data);
    }

    public void sendTree(String treeXml) throws IOException {
        outputStream.writeUTF(treeXml);
    }

    public void sendUserAndNodePermission(String json) throws IOException {
        outputStream.writeUTF(json);
    }

    public void sendPermission(String jsonPermission) throws IOException {
        outputStream.writeUTF(jsonPermission);
    }
}
