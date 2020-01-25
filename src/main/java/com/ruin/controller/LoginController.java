package com.ruin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruin.domain.User;
import com.ruin.utils.HttpUtil;
import com.ruin.utils.Store;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.java_websocket.client.WebSocketClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ruin
 * @date 2020/1/24-13:37
 */
public class LoginController{

    public Button loginButton;
    public PasswordField passwordText;
    public TextField accountText;
    public Label loginInfo;
    private Stage loginStage;

    public void login() throws IOException {
//        获取登陆窗口
        loginStage= (Stage) loginButton.getScene().getWindow();

//        发送登陆请求
        Map map=new HashMap();
        map.put("account",accountText.getText());
        map.put("password",passwordText.getText());
        JSONObject json = HttpUtil.post("/doLogin", JSON.toJSONString(map));
        Integer statusCode= (Integer) json.get("statusCode");

//        如果登陆成功
        if(statusCode==200){
//            将用户存入仓库
            Store.data.put("user",JSON.toJavaObject((JSON) json.get("content"),User.class) );
//            加载主窗体
            Parent root= FXMLLoader.load(getClass().getResource("/main.fxml"));
            Scene scene=new Scene(root);
            Stage mainStage=new Stage();
            mainStage.setScene(scene);
            mainStage.setOnCloseRequest(e->{
                WebSocketClient webSocketClient= (WebSocketClient) Store.data.get("webSocketClient");
                webSocketClient.close();
                System.exit(0);
            });

            mainStage.show();
//            关闭登陆窗体
            loginStage.close();
        }else {
//            提示错误信息
            loginInfo.setText(json.get("extra").toString());
        }

    }



}
