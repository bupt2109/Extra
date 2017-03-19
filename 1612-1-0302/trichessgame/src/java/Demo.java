import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.util.*;

/**
 * Created by
 */
public class Demo {
    JFrame frame;
    TrianglePainter panel;
    JPanel panel1;
    JTextField textfield1;
    JTextField textfield2;
    JButton music;
    JButton huiqi;
    JButton lianxia;

    Game game;
    Player player;
    Position p;
    ChessBoard board;
    String name1;
    String name2;

    public boolean isregret = true; //只能悔一步
    private boolean isdouble = false;//是否用过连下两步的机会
    private boolean ismusic = true;//是否用背景音乐

    public Demo(){
        frame = new JFrame(Game.Name);
        panel = new TrianglePainter();
        game = panel.game;
        player = panel.player;
        board = panel.board;
        panel1 = new JPanel();
        panel.addMouseListener(new mouseAdapter());
        name1 = game.p1.getName();
        name2 = game.p2.getName();

        huiqi = new JButton("悔棋");
        lianxia = new JButton("连下");
        music = new JButton("关闭音乐");
        lianxia.setVisible(false);

        huiqi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isregret) {
                    game.regretMove();
                    isregret = false;
                    panel.repaint();
                }
            }
        });

        lianxia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isregret = false;
                game.changePlayer();
                isdouble = true;
                lianxia.setVisible(false);
            }
        });

        music.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ismusic){
                    ismusic = false;
                    music.setText("打开音乐");
                }
                else{
                    ismusic = true;
                    music.setText("关闭音乐");
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        login();
        //menu();

    }

    public void menu(){
        panel1.removeAll();
        panel1.repaint();
        frame.getContentPane().removeAll();
        frame.repaint();
        JPanel Panel1 = new JPanel();
        JPanel Panel2 = new JPanel();
        JLabel textlabel = new JLabel("玩家1: "+name1,JLabel.CENTER);
        JLabel textlabe2 = new JLabel("玩家2: "+name2,JLabel.CENTER);
        Panel1.setLayout(new GridLayout(2,1));
        Panel1.add(textlabel);
        Panel1.add(textlabe2);
        Panel2.setLayout(new GridLayout(3,1));
        Panel2.add(huiqi);
        Panel2.add(music);
        Panel2.add(lianxia);
        panel1.setLayout(new GridLayout(1,2));
        panel1.add(Panel1);
        panel1.add(Panel2);
        panel1.validate();
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, panel1);
        frame.validate();
    }

    public void login(){
        JPanel loginPanel = new JPanel();
        JLabel textlabel = new JLabel("登录：",JLabel.CENTER);
        JLabel textlabe2 = new JLabel("玩家1姓名：",JLabel.CENTER);
        JLabel textlabe3 = new JLabel("玩家2姓名：",JLabel.CENTER);
        textfield1 = new JTextField(20);
        textfield2 = new JTextField(20);

        JButton loginButton = new JButton("OK");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = textfield1.getText();
                String b = textfield2.getText();
                if( (a!=null) && (!a.equals(""))){
                    game.p1.setName(a);
                }
                if( (b!=null) && (!b.equals(""))){
                    game.p2.setName(b);
                }
                name1 = a;
                name2 = b;
                menu();
            }
        });
        loginPanel.setLayout(new GridLayout(4,2));
        loginPanel.add(textlabel);
        loginPanel.add(new JLabel(""));
        loginPanel.add(textlabe2);
        loginPanel.add(textfield1);
        loginPanel.add(textlabe3);
        loginPanel.add(textfield2);
        loginPanel.add(new JLabel(""));
        loginPanel.add(loginButton);
        frame.getContentPane().add(loginPanel);
        frame.validate();
    }

    public void rank(){
        frame.getContentPane().removeAll();
        frame.repaint();
        JPanel rankPanel = new JPanel();
        JTextArea rankTextarea = new JTextArea(10,20);
        rankTextarea.setLineWrap(true);
        JButton rankButton = new JButton("OK");
        rankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu();
            }
        });

        //将map.entrySet()转换成list
        java.util.List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(game.rankmap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            //按分数降序排序
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        rankTextarea.append("========================\n");
        rankTextarea.append("High Score List:\n");
        rankTextarea.append(String.format("%-15s%-20s\n","Name:","Score:"));
        //Top 5
        int i=0;
        for (Map.Entry<String, Integer> mapping : list) {
            if(i==5)
                break;
            rankTextarea.append(String.format("%-15s%-15d\n",mapping.getKey(),mapping.getValue()));
            i++;
        }
        rankTextarea.append("========================\n");

        rankPanel.add(rankTextarea);
        rankPanel.add(rankButton);
        frame.getContentPane().add(rankPanel);
        frame.validate();
    }

    class mouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();  //得到鼠标x坐标
            int y = e.getY();  //得到鼠标y坐标
            int[] pos = panel.findPos(x, y);
            if (pos != null) {
                ChessBoard board = game.chessBorad;
                Position[][] poses = board.getBoard();
                p = poses[pos[0]][pos[1]];
                player = game.pl;
                if (game.move(p, player) != -1) {
                    lianxia.setVisible(false);
                    if(game.step == 64){
                        JOptionPane.showMessageDialog(null, "无棋可下！双方均未能实现三通！\n开始下一局？", "OK", JOptionPane.INFORMATION_MESSAGE);
                        game.newGame();
                        panel.state = 0;
                        isdouble = false;
                        panel.repaint();
                        return;
                    }
                    if(ismusic){
                        playmusic(player.getXh());
                    }
                    if(!isdouble && player == game.p2){
                        lianxia.setVisible(true);
                    }
                    isregret = true;
                    panel.posX = pos[0];
                    panel.posY = pos[1];
                    panel.state = 1;
                    panel.repaint();
                    boolean win = game.isWin();
                    if (win) {
                        JOptionPane.showMessageDialog(null, player.getName() + " win!\n得分：" + board.getBlockNum() + " \n开始下一局？", "OK", JOptionPane.INFORMATION_MESSAGE);
                        game.updatescore();
                        rank();
                        game.newGame();
                        panel.state = 0;
                        isdouble = false;
                        panel.repaint();
                    } else {
                        board.flushState();
                        game.changePlayer();
                    }
                }
            }
        }
    }

    public void playmusic(int p){
        String waveName;
        if(p==1){
            waveName = "A.wav";
        }
        else{
            waveName = "B.wav";
        }
        try {
            InputStream ws = Demo.class.getResourceAsStream(waveName);
            AudioStream as = new AudioStream(ws);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
    }

}
