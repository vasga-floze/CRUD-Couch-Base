package com.example.couchbaselite_crud;
import com.couchbase.lite.*;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;



import java.net.URI;
import java.net.URISyntaxException;

public class CDBManager {
    String db;
    String server;
    String port;
    String user;
    String pass;
    Context context;
    Database database;

    public CDBManager(Context context, String db, String server,
                      String port, String user, String pass){
        this.context=context;
        this.db = db;
        this.server=server;
        this.port=port;
        this.user=user;
        this.pass=pass;


    }

    public void startDB(){
        DatabaseConfiguration config = new DatabaseConfiguration(context);
        try {
            this.database = new Database(db, config);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();

        }
    }

    public void startReplication(){
        Endpoint targetEndpoint = null;
        try {
            targetEndpoint = new URLEndpoint(new URI("ws://" + server + ":" + port + "/" + db));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert targetEndpoint != null;
        ReplicatorConfiguration replConfig =
                new ReplicatorConfiguration(database, targetEndpoint);
        replConfig.setReplicatorType(ReplicatorConfiguration.ReplicatorType.PUSH_AND_PULL);
        replConfig.setAuthenticator(new BasicAuthenticator(user, pass));
        Replicator replicator = new Replicator(replConfig);
        replicator.start();


    }


    public void changed(@NonNull ReplicatorChange change){
        if (change.getStatus().getError() !=null){
            Log.i("Error", "Error code :: "
                    + change.getStatus().getError().getCode());
        }
    }

}
