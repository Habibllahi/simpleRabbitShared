package com.codetrik;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;



@RequiredArgsConstructor
public class SharedConnectionFactory {
    private final ExecutorService executor;
    private final String host, username, password, connectionName, virtualHost;
    private final int port;
    private static Logger logger = LoggerFactory.getLogger("SharedConnectionFactory");

    public Optional<Connection> createConnection() {
        try {
            var connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(this.host);
            connectionFactory.setPort(this.port);
            connectionFactory.setUsername(this.username);
            connectionFactory.setPassword(this.password);
            connectionFactory.setVirtualHost(this.virtualHost);
            connectionFactory.setSharedExecutor(this.executor);
            connectionFactory.setAutomaticRecoveryEnabled(true);
            connectionFactory.setTopologyRecoveryEnabled(true);
            return Optional.ofNullable(connectionFactory.newConnection(this.connectionName));
        } catch (IOException | TimeoutException e) {
            logger.error(e.getMessage(),e);
           return Optional.empty();
        }
    }

    public static Optional<Channel> createChannel(Connection connection,int channelNumber)  {
        try{
            return connection.openChannel(channelNumber);
        }catch (IOException e){
            logger.error(e.getMessage(),e);
            return Optional.empty();
        }

    }
}
