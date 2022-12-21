package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;

import models.Manager;
import views.DialogConfirmation;
import views.DialogPermission;
import views.WindowMain;

public class Presenter implements ActionListener, IObserver {

    private Manager manager;
    private WindowMain window;
    private DialogConfirmation dialogConfirmation;
    private DialogPermission dialogPermission;
    private boolean isInConference;
    private String actualNode;

    public Presenter() {
        manager = new Manager();
        window = new WindowMain(this);
        dialogConfirmation = new DialogConfirmation(this);
        dialogPermission = new DialogPermission(window);
        isInConference = true;
        windowEvent();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Commands.valueOf(e.getActionCommand())) {
            case LOGIN:
                loginUser();
                break;
            case NO_OPTION:
                dialogConfirmation.setVisible(false);
                break;
            case YES_OPTION:
                closeApp();
                break;
            case ACTION_NODE:
                break;
            case SHOW_CONFERENCES_TREE:
                showConferencesTree();
                break;
            case SHOW_PERMISSIONS_TREE:
                showPermissionsTree();
                break;
            case YES_OPTION_PERMISSION:
                authorizePermission(e);
                break;
            case NO_OPTION_PERMISSION:
                noAuthorizePermission(e);
                break;
        }
    }

    private void loginUser() {
        try {
            manager.initConnection(this, window.getUserNameLogin());
            window.showMenu(manager.getAdminName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeApp(){
        try {
            manager.closeConnection();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void windowEvent(){
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                dialogConfirmation.showWarning(window);
            }
        });
    }

    private void showPermissionsTree(){
        window.showTree(manager.getPermissionsRoot());
        window.showPermissionConventions();
        isInConference = false;
    }

    private void showConferencesTree(){
        window.showTree(manager.getConferencesRoot());
        window.showConferenceConventions();
        isInConference = true;
    }

    private void noAuthorizePermission(ActionEvent e){
        String option = ((JButton)e.getSource()).getName();
        dialogPermission.disableButtons(option);
    }

    private void authorizePermission(ActionEvent e){
        try {
            String option = ((JButton)e.getSource()).getName();
            String fatherName = dialogPermission.disableButtons(option);
            manager.sendOptionPermission(fatherName,actualNode, option);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void updateConferencesTree(ArrayList<String[]> treeData) {
        manager.generateConferencesTree(treeData);
        if(isInConference){
            showConferencesTree();
        }
    }
    
    @Override
    public void updatePermissionsTree(ArrayList<String[]> treeData) {
        manager.generatePermissionsTree(treeData);
        if(!isInConference){
            showPermissionsTree();
        }
    }
    
    @Override
    public void notifyPermission(String[] data) {
        Object[] dataNode = manager.getDataNode(data[1]);
        actualNode = data[1];
        dialogPermission.addComponents(this,data[0],(String)dataNode[0],actualNode);
    }

    public static void main(String[] args) {
        new Presenter();
    }
}
