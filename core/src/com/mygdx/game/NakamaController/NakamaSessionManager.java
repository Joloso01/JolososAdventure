package com.mygdx.game.NakamaController;


import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.heroiclabs.nakama.*;
import com.heroiclabs.nakama.api.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NakamaSessionManager {
    private final DefaultClient client;
    ExecutorService executor = Executors.newSingleThreadExecutor();
    public static Account account;
    private SocketClient socket;
    private Session session;
    private Match match;
    final String[] idPartida = new String[1];

    public interface IniciarSesionCallback {
        void loginOk();
        void loginError(String error);
    }

    public NakamaSessionManager() {
        client = new DefaultClient("mynewkey", "192.168.0.20", 7349, false);
    }

    public void iniciarSesion(String email, String password, String username, IniciarSesionCallback callback){

        ListenableFuture<Session> authFuture = client.authenticateEmail(email, password,username);

        try {
            session = authFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        AsyncFunction<Session, Account> accountFunction = session -> client.getAccount(session);

        ListenableFuture<Account> accountFuture = Futures.transformAsync(authFuture, accountFunction, executor);
        Futures.addCallback(accountFuture, new FutureCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
                NakamaSessionManager.account = account;
                callback.loginOk();
                executor.shutdown();
            }

            @Override
            public void onFailure(Throwable e) {
                callback.loginError(e.getMessage());
                executor.shutdown();
            }

        }, executor);

        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("ERRORRRRR " + e.getMessage());
        }
    }

    public void crearPartida(){
        String host = "192.168.0.20";
        int port = 7350; // different port to the main API port
        socket = client.createSocket(host, port, false);
        SocketListener listener = new AbstractSocketListener() {
            @Override
            public void onChannelMessage(final com.heroiclabs.nakama.api.ChannelMessage message) {
                System.out.format("Received a message on channel %s", message.getChannelId());
                System.out.format("Message content: %s", message.getContent());
            }
        };

        try {
            socket.connect(session,listener).get();
            System.out.println("Socket connected successfully.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        try {
            match = socket.createMatch().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void unirsePartida(){

        try {
            Match match1 = socket.joinMatch(idPartida[0]).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void crearPerfilMatchMatchmaking(){
        String queryRestringida = "+properties.region:europe +properties.rank:>=5 +properties.rank:<=10";
        String queryCualquiera = "*";
        int minCount = 2;
        int maxCount = 4;
        Map<String, String> stringProperties = new HashMap<String, String>() {{
            put("region", "europe");
        }};
        Map<String, Double> numericProperties = new HashMap<String, Double>() {{
            put("rank", 8.0);
        }};

        try {
            MatchmakerTicket matchmakerTicket = socket.addMatchmaker(
                    maxCount, minCount, queryCualquiera, stringProperties, numericProperties).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public String unirseAlMatchMaking(){
        SocketListener listener = new AbstractSocketListener() {
            @Override
            public void onMatchmakerMatched(final MatchmakerMatched matched) {
                try {
                    socket.joinMatchToken(matched.getToken()).get();
                    idPartida[0] = socket.joinMatchToken(matched.getToken()).get().getMatchId();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        return idPartida[0];
    }
}