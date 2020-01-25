package com.ruin.websocket;

import com.ruin.controller.MainController;
import com.ruin.utils.Store;
import javafx.scene.control.TextArea;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * @author ruin
 * @date 2020/1/25-11:35
 */
public class MyWebSocketClient extends WebSocketClient {

    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("成功建立连接");
    }

    @Override
    public void onMessage(String s) {
        System.out.println(s);
        MainController mainController= (MainController) Store.data.get("mainController");
        TextArea textArea=mainController.getChatText();

        if(textArea.getText().equals(""))
            textArea.setText(s);
        else
            textArea.setText(textArea.getText()+"\n"+s);

        textArea.appendText("");

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("关闭连接");
    }

    @Override
    public void onError(Exception e) {
        System.out.println(e.getCause());
    }
}
