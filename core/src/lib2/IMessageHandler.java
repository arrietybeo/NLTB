package lib2;

public interface IMessageHandler {
   void onMessage(Message var1);

   void onConnectionFail();

   void onDisconnected();

   void onConnectOK();
}
