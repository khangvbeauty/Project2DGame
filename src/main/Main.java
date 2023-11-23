package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

      JFrame window = new JFrame();
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Đóng cửa sổ "x"
      window.setResizable(false); //Không thay đổi kích thước
      window.setTitle("2D VK GAME "); //Tiêu đề
      
      GamePanel gamePanel = new GamePanel();
      window.add(gamePanel); // add vào cửa sổ
      
      window.pack();// Fix hợp thành phụ
      
      window.setLocationRelativeTo(null); //Cửa sổ trung tâm
      window.setVisible(true);
      
      gamePanel.startGameThread();
      
      
    }
}
