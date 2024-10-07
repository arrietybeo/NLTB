package lib;

import com.badlogic.gdx.Gdx;
import com.team.ngulong.MyGdxGame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lib2.IMessageHandler;
import lib2.ISession;
import lib2.Message;

public class Session_ME implements ISession {
    protected static Session_ME instance = new Session_ME();
    private DataOutputStream dos;
    public DataInputStream dis;
    public static IMessageHandler messageHandler;
    private mSocket sc;
    public boolean connected;
    public boolean connecting;
    private final Session_ME.Sender sender = new Session_ME.Sender();
    public Thread initThread;
    public Thread collectorThread;
    public Thread sendThread;
    public int sendByteCount;
    public int recvByteCount;
    boolean getKeyComplete;
    public byte[] key = null;
    private byte curR;
    private byte curW;
    long timeConnected;
    public static String host;
    public static int port;
    public String strRecvByteCount = "";
    public static boolean isCancel;
    public static mVector recieveMsg = new mVector();
    int countMsg = 0;
    public static int countRead = 0;

    public static Session_ME gI() {
        return instance;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void setHandler(IMessageHandler messageHandler) {
        Session_ME.messageHandler = messageHandler;
    }

    public static void onRecieveMsg(Message msg) {
        if (Thread.currentThread().getName() == MyGdxGame.mainThreadName) {
            messageHandler.onMessage(msg);
        } else {
            System.out.println("RA NGOAI MAIN THREAD");
            recieveMsg.addElement(msg);
        }

    }

    public void sendMessage(Message message) {
        this.sender.AddMessage(message);
    }

    private synchronized void doSendMessage(Message m) throws IOException {
        byte[] data = m.getData();

        try {
            if (this.getKeyComplete) {
                byte b = this.writeKey(m.command);
                this.dos.writeByte(b);
            } else {
                this.dos.writeByte(m.command);
            }

            if (data != null) {
                int size = data.length;
                if (this.getKeyComplete) {
                    int byte1 = this.writeKey((byte) (size >> 8));
                    this.dos.writeByte(byte1);
                    int byte2 = this.writeKey((byte) (size & 255));
                    this.dos.writeByte(byte2);
                } else {
                    this.dos.writeShort(size);
                }

                if (this.getKeyComplete) {
                    for (int i = 0; i < data.length; ++i) {
                        data[i] = this.writeKey(data[i]);
                    }
                }

                this.dos.write(data);
                this.sendByteCount += 5 + data.length;
            } else {
                this.dos.writeShort(0);
                this.sendByteCount += 5;
            }

            this.dos.flush();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public static void update() {
        while (recieveMsg.size() > 0) {
            Message msg = (Message) recieveMsg.elementAt(0);
            if (msg == null) {
                recieveMsg.removeElementAt(0);
                return;
            }

            messageHandler.onMessage(msg);
            recieveMsg.removeElementAt(0);
        }

    }

    private byte readKey(byte b) {
        byte[] var10000 = this.key;
        byte var10003 = this.curR;
        this.curR = (byte) (var10003 + 1);
        byte i = (byte) (var10000[var10003] & 255 ^ b & 255);
        if (this.curR >= this.key.length) {
            this.curR = (byte) (this.curR % this.key.length);
        }

        return i;
    }

    private byte writeKey(byte b) {
        byte[] var10000 = this.key;
        byte var10003 = this.curW;
        this.curW = (byte) (var10003 + 1);
        byte i = (byte) (var10000[var10003] & 255 ^ b & 255);
        if (this.curW >= this.key.length) {
            this.curW = (byte) (this.curW % this.key.length);
        }

        return i;
    }

    public void close() {
        this.cleanNetwork();
    }

    private void cleanNetwork() {
        this.key = null;
        this.curR = 0;
        this.curW = 0;

        try {
            this.connected = false;
            this.connecting = false;
            if (this.sc != null) {
                this.sc.close();
                this.sc = null;
            }

            if (this.dos != null) {
                this.dos.close();
                this.dos = null;
            }

            if (this.dis != null) {
                this.dis.close();
                this.dis = null;
            }

            this.sendThread = null;
            this.collectorThread = null;
            System.gc();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void connect(String host, String port) {
        if (!this.connected && !this.connecting) {
            this.getKeyComplete = false;
            this.sc = null;
            this.sender.clear();
            this.initThread = new Thread(new Session_ME.NetworkInit(host, Integer.parseInt(port)));
            this.initThread.start();
            Cout.Debug("Connect to Ip " + host + "- " + port);
        }
    }

    class MessageCollector implements Runnable {
        public void run() {
            while (true) {
                try {
                    if (Session_ME.this.isConnected()) {
                        Message message = this.readMessage();
                        final Message msg = message;
                        if (message != null) {
                            try {
                                if (message.command == -40) {
                                    this.getKey(message);
                                    continue;
                                }

                                Gdx.app.postRunnable(new Runnable() {
                                    public void run() {
                                        Session_ME.messageHandler.onMessage(msg);
                                    }
                                });
                            } catch (Exception var4) {
                                var4.printStackTrace();
                            }
                            continue;
                        }
                    }
                } catch (Exception var5) {
                    var5.printStackTrace();
                }

                if (Session_ME.this.connected) {
                    if (Session_ME.messageHandler != null) {
                        if (mSystem.currentTimeMillis() - Session_ME.this.timeConnected > 500L) {
                            Session_ME.messageHandler.onDisconnected();
                        } else {
                            Session_ME.messageHandler.onConnectionFail();
                        }
                    }

                    if (Session_ME.this.sc != null) {
                        Session_ME.this.cleanNetwork();
                    }
                }

                return;
            }
        }

        private void getKey(Message message) throws IOException {
            byte keySize = message.reader().readByte();
            Session_ME.this.key = new byte[keySize];

            int i;
            for (i = 0; i < keySize; ++i) {
                Session_ME.this.key[i] = message.reader().readByte();
            }

            for (i = 0; i < Session_ME.this.key.length - 1; ++i) {
                byte[] var10000 = Session_ME.this.key;
                var10000[i + 1] ^= Session_ME.this.key[i];
            }

            Session_ME.this.getKeyComplete = true;
        }

        private Message readMessage() throws Exception {
            ++Session_ME.countRead;
            byte cmd = Session_ME.this.dis.readByte();
            if (Session_ME.this.getKeyComplete) {
                cmd = Session_ME.this.readKey(cmd);
            }

            int size;
            byte[] data;
            if (Session_ME.this.getKeyComplete) {
                if (cmd == 127) {
                    cmd = Session_ME.this.dis.readByte();
                    cmd = Session_ME.this.readKey(cmd);
                    data = new byte[]{Session_ME.this.dis.readByte(), Session_ME.this.dis.readByte(), Session_ME.this.dis.readByte(), Session_ME.this.dis.readByte()};
                    size = Session_ME.this.readKey(data[3]) & 255 | (Session_ME.this.readKey(data[2]) & 255) << 8 | (Session_ME.this.readKey(data[1]) & 255) << 16 | (Session_ME.this.readKey(data[0]) & 255) << 24;
                } else {
                    byte b1 = Session_ME.this.dis.readByte();
                    byte b2 = Session_ME.this.dis.readByte();
                    size = (Session_ME.this.readKey(b1) & 255) << 8 | Session_ME.this.readKey(b2) & 255;
                }
            } else {
                size = Session_ME.this.dis.readUnsignedShort();
            }

            data = new byte[size];
            int len = 0;
            int byteRead = 0;

            while (len != -1 && byteRead < size) {
                len = Session_ME.this.dis.read(data, byteRead, size - byteRead);
                if (len > 0) {
                    byteRead += len;
                    Session_ME var10000 = Session_ME.this;
                    var10000.recvByteCount += 5 + byteRead;
                }
            }

            if (Session_ME.this.getKeyComplete) {
                for (int i = 0; i < data.length; ++i) {
                    data[i] = Session_ME.this.readKey(data[i]);
                }
            }

            Message msg = new Message(cmd, data);
            return msg;
        }
    }

    class NetworkInit implements Runnable {
        private final String host;
        private final int port;

        NetworkInit(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public void run() {
            Session_ME.isCancel = false;
            (new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(20000L);
                    } catch (InterruptedException var3) {
                    }

                    if (Session_ME.this.connecting) {
                        try {
                            Session_ME.this.sc.close();
                        } catch (Exception var2) {
                        }

                        Session_ME.isCancel = true;
                        Session_ME.this.connecting = false;
                        Session_ME.this.connected = false;
                        Session_ME.messageHandler.onConnectionFail();
                    }

                }
            })).start();
            Session_ME.this.connecting = true;
            Thread.currentThread().setPriority(1);
            Session_ME.this.connected = true;

            try {
                this.doConnect(this.host, this.port);
                Session_ME.messageHandler.onConnectOK();
            } catch (Exception var4) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException var3) {
                }

                if (Session_ME.isCancel) {
                    return;
                }

                if (Session_ME.messageHandler != null) {
                    Session_ME.this.close();
                    Session_ME.messageHandler.onConnectionFail();
                }
            }

        }

        public void doConnect(String host, int port) throws Exception {
            Session_ME.this.sc = new mSocket(host, port);
            Session_ME.this.sc.setKeepAlive(true);
            Session_ME.this.dos = Session_ME.this.sc.getOutputStream();
            Session_ME.this.dis = Session_ME.this.sc.getInputStream();
            (new Thread(Session_ME.this.sender)).start();
            Session_ME.this.collectorThread = new Thread(Session_ME.this.new MessageCollector());
            Session_ME.this.collectorThread.start();
            Session_ME.this.timeConnected = mSystem.currentTimeMillis();
            Session_ME.this.doSendMessage(new Message((byte) -40));
            Session_ME.this.connecting = false;
        }
    }

    private class Sender implements Runnable {
        public final mVector sendingMessage = new mVector();

        public Sender() {
        }

        public void AddMessage(Message message) {
            this.sendingMessage.addElement(message);
        }

        public void removeAllMessage() {
            if (this.sendingMessage != null) {
                this.sendingMessage.removeAllElements();
            }

        }

        public void run() {
            while (true) {
                try {
                    if (Session_ME.this.connected) {
                        if (Session_ME.this.getKeyComplete) {
                            while (this.sendingMessage.size() > 0) {
                                Message m = (Message) this.sendingMessage.elementAt(0);
                                this.sendingMessage.removeElementAt(0);
                                Session_ME.this.doSendMessage(m);
                            }
                        }

                        try {
                            Thread.sleep(10L);
                        } catch (InterruptedException var2) {
                        }
                        continue;
                    }
                } catch (IOException var3) {
                    var3.printStackTrace();
                }

                return;
            }
        }

        public void clear() {
            this.sendingMessage.removeAllElements();
        }
    }
}
