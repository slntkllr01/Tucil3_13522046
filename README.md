# Word Ladder Solver - Tugas Kecil III IF2211 Strategi Algoritma 
> by Raffael Boymian Siahaan (13522046)

## Table of Contents

- [General Information](#general-information)
- [File Structures](#file-structures)
- [Requirement](#requirement)
- [Setup and Usage](#setup-and-usage)
- [Authors](#authors)

## General Information

Word ladder, also known as Doublets, word-links, change-the-word puzzles, paragrams, laddergrams, or word golf, is a popular word game for all ages. It was invented by Lewis Carroll, a writer and mathematician, in 1877. In this game, players are given two words called the start word and the end word. To win the game, players must find a chain of words that connects the start word to the end word. The start and end words always have the same number of letters. Each adjacent word in the chain can only differ by one letter. The objective of the game is to find the optimal solution, which minimizes the number of words in the chain. Below is an illustration and the rules of the game.

The purpose of this third small assignment is to create a solver for this game, with the hope of finding the most optimal solution to complete the Word Ladder game. The program should be written in Java and can be based on a Command Line Interface (CLI) or include a Graphical User Interface (GUI) as an additional bonus. The program must find solutions to the word ladder game using the Uniform Cost Search (UCS), Greedy Best First Search, and A* algorithms.


## File Structures
```
*
│   README.md
│
├───bin
│   │   SearchAlgorithmGUI.class
│   │   WordGrid.class
│   │
│   └───backend
│           AStar$Node.class
│           AStar.class
│           DictLoader.class
│           GBFS$Node.class
│           GBFS.class
│           Heuristic.class
│           UCS$Node.class
│           UCS.class
│           Utility$Result.class
│           Utility.class
│
├───doc
│       Tucil3_13522046.pdf
│
├───src
│   │   SearchAlgorithmGUI.java
│   │   WordGrid.java
│   │
│   └───backend
│           AStar.java
│           dictionary.txt
│           DictLoader.java
│           GBFS.java
│           Heuristic.java
│           UCS.java
│           Utility.java
│
└───test
```

## Requirement
- `Java` 
- `Swing`

## Setup and Usage
1. Clone this repository using command `git clone https://github.com/slntkllr01/Tucil3_13522046.git`
2. Change to root directory using command `cd Tucil3_13522046`
3. Run the program `java -cp bin SearchAlgorithmGUI`
4. Choose the algorithm and fill the start word, end word, and then press 'Start!'