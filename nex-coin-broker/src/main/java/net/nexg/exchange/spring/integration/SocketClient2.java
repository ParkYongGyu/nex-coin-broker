package net.nexg.exchange.spring.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient2 {

    private Socket mSocket;
    private BufferedReader mIn;
    private PrintWriter mOut;

    public SocketClient2(String ip, int port, int limitAccessCnt) {

//        for(int i = 0; i < limitAccessCnt; i++) {
	        try {
	            // ������ ��û ������
	            mSocket = new Socket(ip, port);
	            System.out.println(ip + " �����");
	
	            // ��� �ձ�
	            mIn = new BufferedReader(
	                    new InputStreamReader(mSocket.getInputStream()));
	            mOut = new PrintWriter(mSocket.getOutputStream());
	
	            while(true) {
		            // �޼��� ����
		            mOut.println("Socket Client 2 Response-" + (limitAccessCnt) );
		            mOut.flush();
		            // ���� ���
		            System.out.println(mIn.readLine());
		            /*
		            try { 
		            	Thread.sleep(1000);
		            } catch(InterruptedException ie) {
		            	
		            }
		            */
	            	break;
	            }
	
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        } finally {
	        	/*
	            // ���� �ݱ� (���� ����)
	            try {
	                mSocket.close();
	            } catch (IOException e) {
	                System.out.println(e.getMessage());
	            }
	            */
//	        }
        }
    }



    public static void main(String[] args) {

        String ip = "127.0.0.1";
        int port = Integer.parseInt(args[0]);
        int limitAccessCnt =  Integer.parseInt(args[1]);

        for(int i = 0; i < limitAccessCnt; i++) {
        	new SocketClient2(ip, port, i);
    	}
    }
	
}
