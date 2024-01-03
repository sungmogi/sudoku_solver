# sudoku_solver

### Introduction

This is a personal project that I worked in May 2022 when I was in national service as a conscripted firefighter after I took Java Programming: Solving Problems with Software on Coursera.
It goes over each horizontal line, vertical line, and 3x3 square and checking what numbers can be legally put in each empty slot. 
The program goes over all the possibilities and outputs the only solution that satisfies the invariant (that there can only be unique numbers in each horizontal line, vertical line, and 3x3 square). 

### Demo

Create an instance of Run and select a .txt file that contains the sudoku puzzle that is formatted as so:

<img width="95" alt="Screen Shot 2024-01-02 at 7 08 27 PM" src="https://github.com/sungmogi/sudoku_solver/assets/131221622/a35b5942-34ef-4906-b4ba-ef1f0e2de4c1">

If the program can solve the puzzle, the following will be printed:

<img width="487" alt="Screen Shot 2024-01-02 at 7 10 44 PM" src="https://github.com/sungmogi/sudoku_solver/assets/131221622/95a55f4c-cd87-4376-a5b9-3cdf55fdca82">

If the program cannot solve the puzzle, the following will be printed:

<img width="487" alt="Screen Shot 2024-01-02 at 7 11 36 PM" src="https://github.com/sungmogi/sudoku_solver/assets/131221622/45cc8ad1-d471-4f54-b9af-c349313677fd">

where the line printed below the solution shows the possible options for each slot. 
