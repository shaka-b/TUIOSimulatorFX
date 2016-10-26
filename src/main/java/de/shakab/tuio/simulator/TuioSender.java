package de.shakab.tuio.simulator;

import com.illposed.osc.OSCBundle;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author David Bimamisa.
 */
public class TuioSender {

    private final String sourceId;
    private OSCPortOut sender;
    private int port;
    private int fseqCounter = 0;

    public TuioSender(String  ipAddress, String deviceName) {
      this(ipAddress, 3333, deviceName);
    }

    public TuioSender(String  ipAddress, int port, String deviceName) {
        try {
            this.port = port;
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            sender = new OSCPortOut(inetAddress, port);
            System.out.println("Connect to " + inetAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        System.out.println("NetUtil.LOCAL_IP_ADDRESS="+NetUtil.LOCAL_IP_ADDRESS);
        sourceId = deviceName + "@" + NetUtil.LOCAL_IP_ADDRESS.getHostAddress();

        //TODO start monitor
    }

    public void sendCursorMessage(final List<TuioPoint> tuioTouchPoints){

        OSCBundle cursorBundle = new OSCBundle();

        buildSourceMessage(cursorBundle);
        buildAliveAndSetMessages(cursorBundle, tuioTouchPoints);
        createFrameMessage(cursorBundle);


        try {
            sender.send(cursorBundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFrameMessage(OSCBundle bundle) {
        OSCMessage frameMessage = new OSCMessage("/tuio/2Dcur");
        frameMessage.addArgument("fseq");
        fseqCounter++;
        frameMessage.addArgument(fseqCounter);

        bundle.addPacket(frameMessage);
    }

    private void buildAliveAndSetMessages(OSCBundle bundle, List<TuioPoint> tuioTouchPoints) {
        OSCMessage aliveMessage = new OSCMessage("/tuio/2Dcur");
        aliveMessage.addArgument("alive");

        OSCMessage setMessage = new OSCMessage("/tuio/2Dcur");
        setMessage.addArgument("set");

        for(TuioPoint point : tuioTouchPoints){
            System.out.println("point.getSessionId())="+point.getSessionId());

            // ALIVE Message
            aliveMessage.addArgument(point.getSessionId());


            // SET Message
            setMessage.addArgument(point.getSessionId());
            setMessage.addArgument(point.getX());
            setMessage.addArgument(point.getY());
            setMessage.addArgument(point.getXVel());
            setMessage.addArgument(point.getYVel());
            setMessage.addArgument(point.getAccel());
            bundle.addPacket(setMessage);

            System.out.println("----------- SET MESSAGES ----------");
            System.out.println(setMessage.getArguments());

        }

        bundle.addPacket(aliveMessage);

        System.out.println("----------- ALIVE MESSAGE ----------");
        System.out.println(aliveMessage.getArguments());


    }


    private void buildSourceMessage(OSCBundle bundle) {
        OSCMessage sourceMessage = new OSCMessage("/tuio/2Dcur");
        sourceMessage.addArgument("source");
        sourceMessage.addArgument(sourceId);
        bundle.addPacket(sourceMessage);
    }
}
