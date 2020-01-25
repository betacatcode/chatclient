package com.ruin.controller;

import com.ruin.domain.User;
import com.ruin.utils.Config;
import com.ruin.utils.Store;
import com.ruin.websocket.MyWebSocketClient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.java_websocket.client.WebSocketClient;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ruin
 * @date 2020/1/24-11:32
 */
public class MainController implements Initializable {
    public Button sendButton;
    public Label successInfo;

    public final String serverUrl="ws://"+ Config.target+"/websocket";
    public TextArea sendText;
    public TextArea chatText;

    private MyWebSocketClient webSocketClient;
    User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        user= (User) Store.data.get("user");
//        提示登陆成功
        successInfo.setText(user.getUsername()+" "+successInfo.getText());

//        webSocket连接服务器
        try {
            webSocketClient=new MyWebSocketClient(new URI(serverUrl));
            webSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

//        设置聊天框自动下滚监听方法
        chatText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                chatText.setScrollTop(Double.MAX_VALUE);
            }
        });
//        设置聊天框和发送框自动换行
        sendText.setWrapText(true);
        chatText.setWrapText(true);

        Store.data.put("mainController",this);
        Store.data.put("webSocketClient",webSocketClient);
    }

    public void sendMsgByEnter(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            sendMsg();
        }
    }

    public void cursorReturn(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){{
            sendText.positionCaret(0);
        }}
    }

    public void sendMsg(){
        webSocketClient.send(user.getUsername()+":"+sendText.getText().trim());
        sendText.setText("");
    }

    public TextArea getChatText() {
        return chatText;
    }

    public void setChatText(TextArea chatText) {
        this.chatText = chatText;
    }
}
