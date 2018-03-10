package com.sjtu.ladder.ladder;

import java.util.Scanner;
import java.util.Stack;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Ladder {
	public void printStack(Stack<String>ladder, String word1, String word2) {
		System.out.printf("A ladder from %s back to %s:", word1, word2);
		while(!ladder.isEmpty()) {
			System.out.printf("%s ",ladder.pop());
		}
		System.out.printf("%n");
	}
	
	public void wordLadder(Set<String>dictionary, String word1, String word2) {
		String alpha = "abcdefghijklmnopqrstuvwxyz";//字母表
		String newWord = word1;
		Set<String>usedWord = new HashSet<String>();
		Deque<Stack<String>>allLadder = new LinkedList<Stack<String>>();
		Stack<String>tempLadder = new Stack<String>();
		
		usedWord.add(word1);
		tempLadder.push(word1);
		allLadder.push(tempLadder);
		
		while(true) {
			int count = allLadder.size();
			if(allLadder.isEmpty())break;
			
			for(int i = 0; i < count; i++) {
				tempLadder = allLadder.peekFirst();
				String tempWord = tempLadder.peek();
				
				//单词变换
				for(int j = 0; j < tempWord.length(); j++) {
					newWord = tempWord;
					for(int k = 0; k < 26; k++) {
						StringBuilder sb = new StringBuilder(newWord);
						sb.replace(j, j+1, String.valueOf(alpha.charAt(k)));
						newWord = sb.toString();
						
						if(!usedWord.contains(newWord) && dictionary.contains(newWord)) {
							Stack<String>copyTempLadder = (Stack<String>)tempLadder.clone();
							copyTempLadder.push(newWord);
							allLadder.addLast(copyTempLadder);
							usedWord.add(newWord);
						}
						if(newWord.equals(word2))break;
					}
					if(newWord.equals(word2))break;
				}
				if(newWord.equals(word2))break;
				allLadder.pop();
			}
			if(newWord.equals(word2))break;
		}
		if(newWord.equals(word2)) {
			Ladder la = new Ladder();
			la.printStack(allLadder.peekLast(), word1, word2);
		}
	}
	
	public static void main(String args[]) {
		
		Set<String>dictionary = new HashSet<String>();
		String pathName;
		String word;
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			System.out.print("please input the dictionary file name:");
			pathName = scan.next();
			File file = new File(pathName);
			
			if(file.exists()) {
				try {
					FileReader fr = new FileReader(pathName);
					BufferedReader buffReader = new BufferedReader(fr);
					while((word = buffReader.readLine()) != null) {
						dictionary.add(word);
					}
					buffReader.close();
					break;
				}catch (FileNotFoundException ex){
					ex.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("the file name does't exist!");
		}
		while(true) {
			String word1;
			String word2;
			System.out.print("Word #1(or Enter to quit:");
			
			//读入两个单词
			word1 = scan.next();
			if(word1 == null || word1.length() <= 0)break;
			System.out.print("Word #2(or Enter to quit:");
			word2 = scan.next();
			if(word2 == null || word2.length() <= 0)break;
			
			//将大写一律转化为小写
			word1 = word1.toLowerCase();
			word2 = word2.toLowerCase();
			
			if(word1.length() == word2.length() && !word1.equals(word2) && dictionary.contains(word1) && dictionary.contains(word2)) {
				Ladder ladder = new Ladder();
				ladder.wordLadder(dictionary, word1, word2);
			}
			if(word1.equals(word2)) {
				System.out.println("The two words must be diffrent!");
			}
			
			if(word1.length() != word2.length()) {
				System.out.println("The two words must have the same length!");
			}
		}
		scan.close();
	}
}
