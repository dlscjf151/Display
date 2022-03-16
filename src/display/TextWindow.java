package display;

import display.message.Message;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class TextWindow extends JFrame {
    private String blank = "                                                                                        ";
    private JLabel lblText = new JLabel(blank);
    private JTextField txtText = new JTextField();
    private JSlider sldRbg = new JSlider(JSlider.HORIZONTAL);
    private JSlider sldGbg = new JSlider(JSlider.HORIZONTAL);
    private JSlider sldBbg = new JSlider(JSlider.HORIZONTAL);
    private JSlider sldRfg = new JSlider(JSlider.HORIZONTAL);
    private JSlider sldGfg = new JSlider(JSlider.HORIZONTAL);
    private JSlider sldBfg = new JSlider(JSlider.HORIZONTAL);
    private int size;
    private JComboBox lstFont;
    private JTextField txtSize = new JTextField();
    private JCheckBox chkBold = new JCheckBox("Bold");
    private JCheckBox chkItalic = new JCheckBox("Italic");
    private JRadioButton cmdLeft = new JRadioButton("Left", true);
    private JRadioButton cmdCenter = new JRadioButton("Center");
    private JRadioButton cmdRight = new JRadioButton("Right");

    private MainWindow guimain;

    private List<Message> messageList = new ArrayList<Message>();
    Thread thread = new Thread() {
        public void run() {
            while(true) {
                while(lblText.getLocation().x > -1 * (lblText.getPreferredSize().width+10 - 740)){
                    lblText.setLocation(lblText.getX()-1,lblText.getY());
                    try{
                        Thread.sleep(20);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                String str = blank;
                for(Message message : messageList){
                    str += message.getText()+blank;
                }
                lblText.setText(str);
                lblText.setSize(lblText.getPreferredSize().width + 10, 240);
                lblText.setFont(createNewFont());
                lblText.setLocation(0, 0);
                lblText.setSize(lblText.getPreferredSize().width + 10, 240);
            }
        }
    };

    public TextWindow(MainWindow guimain) {
        this.guimain = guimain;
        setTitle("Sign");
        setSize(750, 500);
        setLocationRelativeTo(null);
        createWindow();
        addListeners();
        createNewFont();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        thread.start();
    }

    private void createWindow() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(createTop(), BorderLayout.NORTH);
        panel.add(createCenter());
        add(panel);
    }

    private JPanel createCenter() {
        lblText.setOpaque(true);
        lblText.setBackground(Color.white);
        lblText.setFont(createNewFont());

        lblText.setLocation(0, 0);
        lblText.setSize(lblText.getPreferredSize().width + 10, 240);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 0, 20, 0));
        JPanel sign = new JPanel(new BorderLayout());
        sign.setBorder(new LineBorder(Color.black));
        JPanel inner = new JPanel(new BorderLayout());
        inner.setLayout(null);
        inner.setBorder(new LineBorder(Color.white, 5));
        inner.add(lblText);
        sign.add(inner);
        panel.add(sign);
        return panel;
    }

    private JLabel createMargin() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(5, 5));
        label.setOpaque(true);
        label.setBackground(Color.white);
        return label;
    }

    private JPanel createDots() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createDot(), BorderLayout.WEST);
        panel.add(createDot(), BorderLayout.EAST);
        panel.add(createMargin());
        return panel;
    }

    private JLabel createDot() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(5, 5));
        label.setOpaque(true);
        label.setBackground(Color.black);
        return label;
    }

    private JPanel createTop() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(createFont());
        panel.add(createFg());
        panel.add(createBg());
        return panel;
    }



    private JPanel createFg() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 0, 5, 0));
        JLabel label = new JLabel("Text color");
        label.setPreferredSize(new Dimension(90, 30));
        panel.add(label, BorderLayout.WEST);
        panel.add(createColors(sldRfg, sldGfg, sldBfg, 0, 0, 0));
        return panel;
    }

    private JPanel createBg() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 0, 5, 0));
        JLabel label = new JLabel("Background");
        label.setPreferredSize(new Dimension(90, 30));
        panel.add(label, BorderLayout.WEST);
        panel.add(createColors(sldRbg, sldGbg, sldBbg, 255, 255, 255));
        return panel;
    }

    private JPanel createFont() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBorder(new EmptyBorder(5, 0, 5, 0));
        panel.add(createFonts());
        JPanel east = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));

        txtSize.setPreferredSize(new Dimension(40, 25));
        txtSize.setText("30");
        txtSize.setEnabled(false);

        east.add(chkBold);
        east.add(chkItalic);

        panel.add(east, BorderLayout.EAST);
        return panel;
    }

    private JPanel createColors(JSlider sldRed, JSlider sldGreen, JSlider sldBlue, int red, int green, int blue) {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.add(createColor("Red", 40, sldRed, 0, 255, red));
        panel.add(createColor("Green", 60, sldGreen, 0, 255, green));
        panel.add(createColor("Blue", 40, sldBlue, 0, 255, blue));
        return panel;
    }

    private JPanel createColor(String text, int width, JSlider slider, int min, int max, int value) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(" " + text);
        label.setPreferredSize(new Dimension(width, 30));
        panel.add(label, BorderLayout.WEST);
        slider.setMinimum(min);
        slider.setMaximum(max);
        slider.setValue(value);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        panel.add(slider);
        return panel;
    }

    private JPanel createFonts() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        JLabel label = new JLabel("Font");
        panel.add(label, BorderLayout.WEST);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (int i = 0; i < fonts.length; ++i)
            model.addElement(fonts[i]);
        lstFont = new JComboBox(model);
        Font df = label.getFont();
        for (int n = 0; n < fonts.length; ++n)
            if (df.getFamily().equals(fonts[n])) {

                lstFont.setSelectedIndex(n);
                break;
            }
        panel.add(lstFont);
        chkBold.setSelected(df.isBold());
        chkItalic.setSelected(df.isItalic());
        txtSize.setText("" + (size = df.getSize()));
        return panel;
    }

    private Font createNewFont() {
        boolean bold = chkBold.isSelected();
        boolean italic = chkItalic.isSelected();
        String name = (String) lstFont.getSelectedItem();
        int size = Integer.parseInt(txtSize.getText());
        if (bold && italic)
            return new Font(name, Font.BOLD | Font.ITALIC, size);
        if (bold)
            return new Font(name, Font.BOLD, size);
        if (italic)
            return new Font(name, Font.ITALIC, size);
        return new Font(name, Font.PLAIN, size);
    }



    private void foreground() {
        Color color = new Color(sldRfg.getValue(), sldGfg.getValue(), sldBfg.getValue());
        lblText.setForeground(color);
        guimain.SaveForeground(color);
    }

    private void background() {
        Color color = new Color(sldRbg.getValue(), sldGbg.getValue(), sldBbg.getValue());
        lblText.setBackground(color);
        guimain.SaveBackground(color);
    }

    private void addListeners() {
        txtText.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                lblText.setText(txtText.getText());
            }

            public void focusGained(FocusEvent e) {
            }
        });

        lstFont.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Font font = createNewFont();
                lblText.setFont(font);
                guimain.SaveFont(font);
            }
        });
        chkBold.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Font font = createNewFont();
                lblText.setFont(font);
                guimain.SaveFont(font);
            }
        });
        chkItalic.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Font font = createNewFont();
                lblText.setFont(font);
                lblText.setSize(lblText.getPreferredSize().width + 10, 240);
                guimain.SaveFont(font);
            }
        });

        sldRfg.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                foreground();
            }
        });
        sldGfg.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                foreground();
            }
        });
        sldBfg.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                foreground();
            }
        });
        sldRbg.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                background();
            }
        });
        sldGbg.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                background();
            }
        });
        sldBbg.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                background();
            }
        });
    }

    public void UpdateMessages(List<Message> messageList){
        this.messageList = messageList;
    }
}