import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Main{
    JFrame frame;
    JTextField inputbet;
    JLabel Bet_Info;
    JLabel Money_Info;
    JLabel PlayerN;

    JTextField NewName;
    JFrame NameFrame;

    JFrame WinLoseFrame;
    JPanel WinLosePanel;
    JLabel Quote = new JLabel();

    JFrame EndGame;
    JPanel EndPanel;

    JPanel HoldPanel;

    JButton btn_rpcard1 = new JButton("Replace Card 1");
    JButton btn_rpcard2 = new JButton("Replace Card 2");
    JButton btn_rpcard3 = new JButton("Replace Card 3");
    JButton btn_start = new JButton("Start");
    JButton btn_result = new JButton("Result");

    JLabel label_img1 = new JLabel();
    JLabel label_img2 = new JLabel();
    JLabel label_img3 = new JLabel();
    JLabel label_img4 = new JLabel();
    JLabel label_img5 = new JLabel();
    JLabel label_img6 = new JLabel();

    ImageIcon img1 = new ImageIcon("Images/card_back.gif");
    ImageIcon img2 = new ImageIcon("Images/card_back.gif");
    ImageIcon img3 = new ImageIcon("Images/card_back.gif");
    ImageIcon img4 = new ImageIcon("Images/card_back.gif");
    ImageIcon img5 = new ImageIcon("Images/card_back.gif");
    ImageIcon img6 = new ImageIcon("Images/card_back.gif");
    
    Money money;

    ArrayList<Card> Deck;
    Card DealerCard1;
    Card DealerCard2;
    Card DealerCard3;
    Card PlayerCard1;
    Card PlayerCard2;
    Card PlayerCard3;

    int changecount = 0;

    public static void main(String[] args) throws Exception {
        Main Game = new Main();
        Game.init();
    }

    public void DeckShuffle(){
        Deck = new ArrayList<Card>();
        int pts;
        for(int i=1;i<5;i++){
            for(int j=1;j<14;j++){
                String address = "Images/card_"+ i + j +".gif";
                if(j>=11) pts = 1000;
                else pts = j;
                Deck.add(new Card(address,pts));
            }
        }
        Collections.shuffle(Deck);
    }

    public void Resetimg(){
        img1 = new ImageIcon("Images/card_back.gif");
        img2 = new ImageIcon("Images/card_back.gif");
        img3 = new ImageIcon("Images/card_back.gif");
        img4 = new ImageIcon("Images/card_back.gif");
        img5 = new ImageIcon("Images/card_back.gif");
        img6 = new ImageIcon("Images/card_back.gif");

        label_img1.setIcon(img1);
        label_img2.setIcon(img2);
        label_img3.setIcon(img3);
        label_img4.setIcon(img4);
        label_img5.setIcon(img5);
        label_img6.setIcon(img6);
    }
    public void init(){
        // create and init components
        JLabel DealerN = new JLabel("Dealer");
        PlayerN = new JLabel("You");

        btn_rpcard1.setEnabled(false);
        btn_rpcard2.setEnabled(false);
        btn_rpcard3.setEnabled(false);
        btn_result.setEnabled(false);

        btn_rpcard1.addActionListener(new rp1Listener());
        btn_rpcard2.addActionListener(new rp2Listener());
        btn_rpcard3.addActionListener(new rp3Listener());
        btn_start.addActionListener(new StartListener());
        btn_result.addActionListener(new ResultListener());

        inputbet = new JTextField(10);

        JLabel BetText = new JLabel("Bet: $");
        Bet_Info = new JLabel("");
        Money_Info = new JLabel("");

        // init imgs
        label_img1.setIcon(img1);
        label_img2.setIcon(img2);
        label_img3.setIcon(img3);
        label_img4.setIcon(img4);
        label_img5.setIcon(img5);
        label_img6.setIcon(img6);
        
        // set Panel    
        JPanel MainP = new JPanel();
        MainP.setLayout(new GridLayout(5,1));

        JPanel Dealer = new JPanel();
        Dealer.setLayout(new BoxLayout(Dealer,BoxLayout.Y_AXIS));

        JPanel Player = new JPanel();
        Player.setLayout(new BoxLayout(Player,BoxLayout.Y_AXIS));

        JPanel RepC = new JPanel();
        JPanel Control = new JPanel();
        JPanel Info = new JPanel();

        JPanel DealerName = new JPanel();
        JPanel DealerCard = new JPanel();
        JPanel PlayerName = new JPanel();
        JPanel PlayerCard = new JPanel();
        
        //Add stuff to frames
        Dealer.add(DealerName);
        Dealer.add(DealerCard);

        Player.add(PlayerCard);
        Player.add(PlayerName);

        RepC.add(btn_rpcard1);
        RepC.add(btn_rpcard2);
        RepC.add(btn_rpcard3);

        Control.add(BetText);
        Control.add(inputbet);
        Control.add(btn_start);
        Control.add(btn_result);

        Info.add(Bet_Info);
        Info.add(Money_Info);

        DealerName.add(DealerN);
        PlayerName.add(PlayerN);

        DealerCard.add(label_img1);
        DealerCard.add(label_img2);
        DealerCard.add(label_img3);

        PlayerCard.add(label_img4);
        PlayerCard.add(label_img5);
        PlayerCard.add(label_img6);

        MainP.add(Dealer);
        MainP.add(Player);
        MainP.add(RepC);
        MainP.add(Control);
        MainP.add(Info);

        //init menubar
        JMenuBar Bar = new JMenuBar();
        JMenu menu = new JMenu("Control");
        JMenuItem ChangeName = new JMenuItem("Change Name");
        JMenuItem Exit = new JMenuItem("Exit");

        menu.add(ChangeName);
        menu.add(Exit);
        Bar.add(menu);
        ChangeName.addActionListener(new ChangeNameListener());
        Exit.addActionListener(new ExitListener());

        // init frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(MainP);
        frame.setTitle("A Simple Card Game");
        frame.setSize(400, 700);
        frame.setVisible(true);
        frame.setJMenuBar(Bar);

        //game
        money = new Money();
        Bet_Info.setText("Please place your bet!"); 
        Money_Info.setText("Amount of money you have: $" + money.getMoney());
    }

    class ChangeNameListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            NameFrame = new JFrame();
            NameFrame.setTitle("New Name");
            NameFrame.setSize(200,150);
            NameFrame.setVisible(true);

            JPanel Panel2 = new JPanel();
            Panel2.setLayout(new BorderLayout());

            JPanel NameP = new JPanel();

            JLabel EnterName = new JLabel("Enter Name: ");
            NewName = new JTextField(20);
            JButton Confirm = new JButton("Confirm");
            Confirm.addActionListener(new ConfirmListener());

            NameP.add(EnterName);
            NameP.add(NewName);
            
            NameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            NameFrame.getContentPane().add(Panel2);
            Panel2.add(NameP,BorderLayout.CENTER);
            Panel2.add(Confirm,BorderLayout.SOUTH);

        }
    }
    class ConfirmListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            PlayerN.setText(NewName.getText());
            NameFrame.dispose();
       }
    }

    
    class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            frame.dispose();
        }
    }

    class StartListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            int s = Integer.parseInt(inputbet.getText());
            changecount = 0;

            if(s>money.getMoney()){
                JFrame Frame2 = new JFrame();
                Frame2.setTitle("Warning");
                Frame2.setSize(200,100);
                Frame2.setVisible(true);

                JPanel Panel2 = new JPanel();
                JLabel NoMoney = new JLabel("Not enough money!");
                Frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Frame2.getContentPane().add(Panel2);
                Panel2.add(NoMoney);
            }

            else{
                Bet_Info.setText("Your current bet is: $" + s);
                inputbet.setEditable(false);

                //Activate buttons
                btn_rpcard1.setEnabled(true);
                btn_rpcard2.setEnabled(true);
                btn_rpcard3.setEnabled(true);
                btn_result.setEnabled(true);
                btn_start.setEnabled(false);

                //Distribute Cards
                DeckShuffle();
                DealerCard1 = Deck.get(0);
                PlayerCard1 = Deck.get(1);
                DealerCard2 = Deck.get(2);
                PlayerCard2 = Deck.get(3);
                DealerCard3 = Deck.get(4);
                PlayerCard3 = Deck.get(5);
                for(int i=0;i<6;i++) Deck.remove(0);
                
                img1 = new ImageIcon(DealerCard1.getName());
                img2 = new ImageIcon(DealerCard2.getName());
                img3 = new ImageIcon(DealerCard3.getName());
                img4 = new ImageIcon(PlayerCard1.getName());
                img5 = new ImageIcon(PlayerCard2.getName());
                img6 = new ImageIcon(PlayerCard3.getName());
                label_img4.setIcon(img4);
                label_img5.setIcon(img5);
                label_img6.setIcon(img6);
            }
        }
    }

    class rp1Listener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            btn_rpcard1.setEnabled(false);

            changecount++;
            if(changecount == 2){
                btn_rpcard2.setEnabled(false);
                btn_rpcard3.setEnabled(false);
            }

            PlayerCard1 = Deck.get(0);
            Deck.remove(0);

            img4 = new ImageIcon(PlayerCard1.getName());
            label_img4.setIcon(img4);
        }
    }

    class rp2Listener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            btn_rpcard2.setEnabled(false);

            changecount++;
            if(changecount == 2){
                btn_rpcard1.setEnabled(false);
                btn_rpcard3.setEnabled(false);
            }
            PlayerCard2 = Deck.get(0);
            Deck.remove(0);

            img5 = new ImageIcon(PlayerCard2.getName());
            label_img5.setIcon(img5);
        }
    }

    class rp3Listener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            btn_rpcard3.setEnabled(false);

            changecount++;
            if(changecount == 2){
                btn_rpcard1.setEnabled(false);
                btn_rpcard2.setEnabled(false);
            }
            PlayerCard3 = Deck.get(0);
            Deck.remove(0);

            img6 = new ImageIcon(PlayerCard3.getName());
            label_img6.setIcon(img6);
        }
    }

    class ResultListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            btn_rpcard1.setEnabled(false);
            btn_rpcard2.setEnabled(false);
            btn_rpcard3.setEnabled(false);
            btn_result.setEnabled(false);

            label_img1.setIcon(img1);
            label_img2.setIcon(img2);
            label_img3.setIcon(img3);

            int dealpt = 0;
            int playpt = 0;

            dealpt += DealerCard1.getPoints();
            dealpt += DealerCard2.getPoints();  
            dealpt += DealerCard3.getPoints();
            
            playpt += PlayerCard1.getPoints();
            playpt += PlayerCard2.getPoints();
            playpt += PlayerCard3.getPoints();

            if(playpt > dealpt){
                money.WinLose(1,Integer.parseInt(inputbet.getText()));
                WinLoseFrame = new JFrame();
                WinLoseFrame = new JFrame();
                WinLosePanel = new JPanel();
                Quote.setText("Congratulations! You Win This Round!");
                JButton OK = new JButton("OK");

                WinLoseFrame.setTitle("Congratulations!");
                WinLoseFrame.setSize(250,150);
                WinLoseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                HoldPanel = new JPanel();
                HoldPanel.setLayout(new BorderLayout());
                HoldPanel.add(WinLosePanel,BorderLayout.CENTER);

                WinLoseFrame.setContentPane(HoldPanel);
                WinLosePanel.setLayout(new BoxLayout(WinLosePanel,BoxLayout.Y_AXIS));
                WinLosePanel.add(Quote);
                WinLosePanel.add(OK);
                OK.addActionListener(new okListener());
                WinLoseFrame.setVisible(true);             
            }

            else{
                money.WinLose(0,Integer.parseInt(inputbet.getText()));
                WinLoseFrame = new JFrame();
                WinLosePanel = new JPanel();
                Quote.setText("Sorry! The Dealer wins this round!");
                JButton OK = new JButton("OK");
                
                WinLoseFrame.setTitle("You Lost!");
                WinLoseFrame.setSize(250,150);
                WinLosePanel.setLayout(new BoxLayout(WinLosePanel,BoxLayout.Y_AXIS));
                WinLoseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                HoldPanel = new JPanel();
                HoldPanel.setLayout(new BorderLayout());
                HoldPanel.add(WinLosePanel,BorderLayout.CENTER);

                WinLoseFrame.setContentPane(HoldPanel);
                WinLosePanel.add(Quote);
                WinLosePanel.add(OK);
                OK.addActionListener(new okListener());
                WinLoseFrame.setVisible(true);
            }

        }
    }

    class okListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            WinLoseFrame.dispose();
            Resetimg();

            if(money.getMoney() == 0){
                EndGame = new JFrame();
                EndPanel = new JPanel();
                EndGame.setTitle("Game Over!");
                EndGame.setSize(300,250);
                EndPanel.setLayout(new BoxLayout(EndPanel,BoxLayout.Y_AXIS));
                EndGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                EndGame.setVisible(true);

                JLabel GG = new JLabel("Game Over!");
                JLabel GG2 = new JLabel("You have no more money!");
                JLabel GG3 = new JLabel("Please start a new game!");
                JButton OK2 = new JButton("OK");
                OK2.addActionListener(new ok2Listener());              

                HoldPanel = new JPanel();
                HoldPanel.setLayout(new BorderLayout());
                HoldPanel.add(EndPanel,BorderLayout.CENTER);

                EndGame.setContentPane(HoldPanel);
                EndPanel.add(GG);
                EndPanel.add(GG2);
                EndPanel.add(GG3);
                EndPanel.add(OK2);       
            }

            else{
                Bet_Info.setText("Please place your bet!"); 
                Money_Info.setText("Amount of money you have: $" + money.getMoney());
                btn_start.setEnabled(true);
                inputbet.setEditable(true);
            }
        }
    }

    class ok2Listener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            EndGame.dispose();
            Bet_Info.setText("Game Over!");
            Money_Info.setVisible(false);
        }
    }
}
