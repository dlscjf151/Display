package display;

import display.board.DummyBoard;
import display.client.Client;
import display.client.UnknownClientException;
import display.message.Message;
import display.message.UrgentMessage;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*******************************************************************************
 * This generates the graphical user interface through which the user interacts
 * with the system.
 *
 * @author Simon Kendal
 * @version 1.0 (26 June 2009)
 ******************************************************************************/
public class MainWindow {

    private JTextField clientClientID;

    private JTextField clientName;

    private JTextField clientAddress;

    private JTextField clientPhone;

    private JTextField clientCredits;

    private JTextField messageClientID;

    private JTextField messageText;

    private JTextField messageDays;

    Manager manager = new Manager();

    private Font font;
    private Color BgColor;
    private Color FgColor;

    private TextWindow textWindow;
    /**
     * This creates the GUI, set up the 3 panels and adds buttons, text boxes and
     * labels
     */
    public MainWindow() {
        // main frame
        textWindow = new TextWindow(this);

        JFrame frame = new JFrame("Message Management System v2.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.setLayout(new GridLayout(1, 3));

        // client panel
        JPanel cliPan = new JPanel();
        cliPan.setLayout(new GridLayout(7, 2));
        cliPan.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        cliPan.add(new JLabel("고객 추가"));
        cliPan.add(new JLabel(""));
        cliPan.add(new JLabel("ID"));
        cliPan.add(clientClientID = new JTextField());
        cliPan.add(new JLabel("이름"));
        cliPan.add(clientName = new JTextField());
        cliPan.add(new JLabel("주소"));
        cliPan.add(clientAddress = new JTextField());
        cliPan.add(new JLabel("전화번호"));
        cliPan.add(clientPhone = new JTextField());
        cliPan.add(new JLabel("충전 금액"));
        cliPan.add(clientCredits = new JTextField());

        cliPan.add(new JLabel(""));
        JButton jb = new JButton("추가");
        jb.addActionListener(new AddClientListener());
        cliPan.add(jb);

        cp.add(cliPan);

        // message panel
        JPanel msgPan = new JPanel();
        msgPan.setLayout(new GridLayout(6, 2));
        msgPan.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        msgPan.add(new JLabel("메세지 추가"));
        msgPan.add(new JLabel(""));
        msgPan.add(new JLabel("ID"));
        msgPan.add(messageClientID = new JTextField());
        msgPan.add(new JLabel("메세지"));
        msgPan.add(messageText = new JTextField());
        msgPan.add(new JLabel("기간"));
        msgPan.add(messageDays = new JTextField());

        msgPan.add(new JLabel(""));
        jb = new JButton("일반 메세지 추가");
        jb.addActionListener(new AddMessageListener());
        msgPan.add(jb);

        msgPan.add(new JLabel(""));
        jb = new JButton("긴급 메세지 추가");
        jb.addActionListener(new AddUrgentMessageListener());
        msgPan.add(jb);

        cp.add(msgPan);

        // button panel
        JPanel btnPan = new JPanel();
        btnPan.setLayout(new GridLayout(8, 1));
        btnPan.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        jb = new JButton("고객 검색");
        jb.addActionListener(new FindClientListener());
        btnPan.add(jb);

        jb = new JButton("충전");
        jb.addActionListener(new IncreaseCreditListener());
        btnPan.add(jb);

        jb = new JButton("고객 제거");
        jb.addActionListener(new DeleteClientListener());
        btnPan.add(jb);

        btnPan.add(new JLabel());

        jb = new JButton("메세지 개수");
        jb.addActionListener(new DisplayMessagesListener());
        btnPan.add(jb);

        jb = new JButton("메세지 제거");
        jb.addActionListener(new PurgeMessagesListener());
        btnPan.add(jb);

        btnPan.add(new JLabel());

        jb = new JButton("저장 후 나가기");
        jb.addActionListener(new SaveAndExitListener());
        btnPan.add(jb);

        cp.add(btnPan);

        frame.pack();
        frame.setVisible(true);

        manager.startUp();
    }

    /**
     * The main method - invoked when the grogram is run
     */
//    public static void main(String[] args) {
//////        new MainWindow(this);
////        GUImain uim = new GUImain();
////        uim.createGUI();
//    }

    private class AddClientListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            try {

                if (clientAddress.getText().equals("") || clientClientID.getText().equals("")
                        || clientCredits.getText().equals("") || clientName.getText().equals("")
                        || clientPhone.getText().equals(""))
                    throw new Exception("빈칸이 존재합니다.");
                Client c = new Client(clientName.getText(), clientAddress.getText(), clientPhone.getText(),
                        Integer.parseInt(clientCredits.getText()));
                try {
                    Client cl = manager.getClientBook().getClient(clientClientID.getText());
                    JOptionPane.showMessageDialog(null, "이미 사용중인 ID입니다.");
                    return;
                } catch (UnknownClientException e) {

                }
                manager.getClientBook().addClient(clientClientID.getText(), c);

                JOptionPane.showMessageDialog(null, "고객 정보가 정상적으로 추가되었습니다.");
                clientAddress.setText("");
                clientClientID.setText("");
                clientCredits.setText("");
                clientName.setText("");
                clientPhone.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "잘못 입력하였습니다.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    private class FindClientListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            String id = JOptionPane.showInputDialog(null, "ID 입력");
            if (id == null)
                id = ""; // workaround in case Cancel pressed

            try {
                JOptionPane.showMessageDialog(null, manager.getClientBook().getClient(id).toString());
            } catch (UnknownClientException uke) {
                JOptionPane.showMessageDialog(null, "해당 고객이 존재하지 않습니다.");
            }

        }
    }

    private class IncreaseCreditListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            String id = JOptionPane.showInputDialog(null, "ID 입력");
            if (id == null)
                id = ""; // workaround in case Cancel pressed

            try {
                Client c = manager.getClientBook().getClient(id);
                String raw;
                int extraCredit;
                raw = JOptionPane.showInputDialog(null, "충전할 금액");
                if (raw == null)
                    raw = ""; // workaround in case Cancel pressed
                try {
                    extraCredit = Integer.parseInt(raw);
                } catch (NumberFormatException exception) {
                    extraCredit = 0;
                }
                c.increaseCredit(extraCredit);
            } catch (UnknownClientException uce) {
                JOptionPane.showMessageDialog(null, "해당 고객이 존재하지 않습니다.");
            }
        }
    }

    private class DeleteClientListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            String id = JOptionPane.showInputDialog(null, "ID 입력");
            if (id == null)
                id = ""; // workround in case Cancel pressed

            try {
                manager.getClientBook().deleteClient(id);
                JOptionPane.showMessageDialog(null, "제거되었습니다.");
            } catch (UnknownClientException uke) {
                JOptionPane.showMessageDialog(null, "해당 고객이 존재하지 않습니다.");
            }
        }
    }

    private class AddMessageListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            try {
                if (messageClientID.getText().equals("") || messageText.getText().equals("")
                        || messageDays.getText().equals("")) {
                    throw new Exception("빈칸이 존재합니다.");
                }
                Client cl = manager.getClientBook().getClient(messageClientID.getText());
                Message m = new Message(messageClientID.getText(), messageText.getText(),
                        Integer.parseInt(messageDays.getText()));
                manager.getMessageSet().addMessage(m);
                messageDays.setText("");
                messageText.setText("");
                messageClientID.setText("");
                JOptionPane.showMessageDialog(null, "추가되었습니다.");
                DummyBoard db = new DummyBoard(textWindow);
                manager.getMessageSet().display(db);
            } catch (UnknownClientException e) {
                JOptionPane.showMessageDialog(null, "해당 고객이 존재하지 않습니다.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "잘못 입력하였습니다.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    private class AddUrgentMessageListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            try {
                if (messageClientID.getText().equals("") || messageText.getText().equals("")
                        || messageDays.getText().equals("")) {
                    throw new Exception("빈칸이 존재합니다.");
                }
                Client cl = manager.getClientBook().getClient(messageClientID.getText());
                Message m = new UrgentMessage(messageClientID.getText(), messageText.getText(),
                        Integer.parseInt(messageDays.getText()));
                manager.getMessageSet().addMessage(m);
                messageDays.setText("");
                messageText.setText("");
                messageClientID.setText("");
                JOptionPane.showMessageDialog(null, "추가되었습니다.");
                DummyBoard db = new DummyBoard(textWindow);
                manager.getMessageSet().display(db);
            } catch (UnknownClientException e) {
                JOptionPane.showMessageDialog(null, "해당 고객이 존재하지 않습니다.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "잘못 입력하였습니다.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    private class DisplayMessagesListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            int count = manager.getMessageSet().CountOfMessages();
            JOptionPane.showMessageDialog(null, "총 "+count+"개의 메세지가 있습니다.");
        }
    }

    private class PurgeMessagesListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            manager.getMessageSet().dailyPurge();
            DummyBoard db = new DummyBoard(textWindow);
            manager.getMessageSet().display(db);
        }
    }

    private class SaveAndExitListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            manager.shutDown();
            System.exit(0);
        }
    }

    public void SaveFont(Font font) {
        this.font = font;
    }

    public void SaveForeground(Color color) {
        this.FgColor = color;
    }

    public void SaveBackground(Color color) {
        this.BgColor = color;
    }
}