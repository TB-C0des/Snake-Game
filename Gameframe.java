/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Snakegame;

import javax.swing.JFrame;
public class Gameframe extends JFrame{
      Gameframe(){
          add(new Outer_Panel());
          setTitle("Snake Game");
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setResizable(false);
          pack();
          setVisible(true);
          setLocationRelativeTo(null);          
      }
      }

