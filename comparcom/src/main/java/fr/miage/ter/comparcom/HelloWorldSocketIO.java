package fr.miage.ter.comparcom;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldSocketIO implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldSocketIO.class);

    private final SocketIOServer server;

    @Autowired
    public HelloWorldSocketIO(SocketIOServer server){
        this.server = server;
    }

    @Override
    public void run(String... args) throws Exception{
        server.start();
        SocketIONamespace namespace = server.addNamespace("/socketio");
        namespace.addConnectListener(onConnect());
        namespace.addDisconnectListener(onDisconnect());
        namespace.addEventListener("helloSpringSocketIO", String.class, onMessageReceived());
    }

    private DataListener<String> onMessageReceived(){
        return (client, data, ackSender) -> {
            log.info("Client {} - Sended {}", client.getSessionId(), data);
            client.sendEvent("helloJSSocketIO", "Hello back from Spring SocketIO");
        };
    }

    private ConnectListener onConnect(){
        return client -> {
            HandshakeData handshakeData = client.getHandshakeData();
            log.info("Client {} - connected", client.getSessionId());
        };
    }

    private DisconnectListener onDisconnect(){
        return client -> {
            log.info("Client {} - disconnected", client.getSessionId());
        };
    }

}
