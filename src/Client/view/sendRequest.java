package Client.view;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class sendRequest {
	public String Fetch(String json,String url,int port) {
        String res="";
        String sentence_to_server;
        try {
            Socket socket = new Socket(url, port);
            sentence_to_server = json;
            DataOutputStream dataSendToServer = new DataOutputStream(socket.getOutputStream());
			
			DataInputStream dataInputFromServer = new DataInputStream(socket.getInputStream());
			
			dataSendToServer.writeUTF(sentence_to_server);
			dataSendToServer.flush();
//            outToServer.write(sentence_to_server + '\n');
//            outTo Server.flush();
            res = dataInputFromServer.readUTF();
            socket.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return res;
    }
}
