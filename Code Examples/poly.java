import java.util.*;
import java.io.*;

class LinkedList {
  
  Node head = new Node();

  static class Node {

    int num;
    double thing;

    public Node() {
      num = 1;
      thing = 1.2;
    }
  }
}


class Main {

  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    LinkedList.Node node = new LinkedList.Node();
  }  
}
